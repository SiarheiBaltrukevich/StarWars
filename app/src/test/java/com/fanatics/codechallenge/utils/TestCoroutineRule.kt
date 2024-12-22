package com.fanatics.codechallenge.utils

import com.fanatics.codechallenge.util.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

/**
 * Test rule that change main dispatcher to test one.
 * Provides [dispatchersProvider] with mocked test dispatchers that should be used in testing components
 * */
@OptIn(ExperimentalCoroutinesApi::class)
class TestCoroutineRule(
    private val testScheduler: TestCoroutineScheduler = TestCoroutineScheduler(),
    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher(testScheduler)
) : TestWatcher() {

    val dispatchersProvider: DispatcherProvider = object : DispatcherProvider {
        override val main: CoroutineDispatcher
            get() = testDispatcher
        override val io: CoroutineDispatcher
            get() = testDispatcher
        override val default: CoroutineDispatcher
            get() = testDispatcher
    }

    val coroutineScope = TestScope(testDispatcher)

    override fun starting(description: Description?) {
        super.starting(description)
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        Dispatchers.resetMain()
    }
}