package com.fanatics.codechallenge.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.simpleName, "Error related to the VM scope.", throwable)
    }

    protected val safeViewModelScope: CoroutineScope = viewModelScope + errorHandler

    private val _errorTimeOutFlow: MutableStateFlow<Boolean> = MutableStateFlow(false)
    protected val errorAppearanceTimeOutFlow: Flow<Boolean>
        get() = run {
            resetLoadingTimeOut()
            return@run _errorTimeOutFlow.asStateFlow()
        }

    protected fun resetLoadingTimeOut() {
        safeViewModelScope.launch {
            delay(3000)
            _errorTimeOutFlow.emit(true)
        }
    }
}
