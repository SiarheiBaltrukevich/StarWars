package com.fanatics.codechallenge.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.plus

abstract class BaseViewModel : ViewModel() {

    private val errorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(this::class.simpleName, "Error related to the VM scope.", throwable)
    }

    protected val safeViewModelScope: CoroutineScope = viewModelScope + errorHandler
}
