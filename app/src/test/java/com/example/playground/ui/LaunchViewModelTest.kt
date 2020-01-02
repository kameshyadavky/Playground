package com.example.playground.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.playground.util.SyncTaskExecutorRule
import com.example.test_shared.MainCoroutineRule
import org.junit.Rule

/**
 * Unit tests for the [LaunchViewModel].
 */
class LaunchViewModelTest {
    // Executes tasks in the Architecture Components in the same thread
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    // Executes tasks in a synchronous [TaskScheduler]
    @get:Rule
    var syncTaskExecutorRule = SyncTaskExecutorRule()

    // Overrides Dispatchers.Main used in Coroutines
    @get:Rule
    var coroutineRule = MainCoroutineRule()


}