package com.example.foody.utils

import androidx.activity.ComponentActivity
import androidx.lifecycle.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

fun <T>LiveData <T>.observeOnce(lifecycleOwner: LifecycleOwner , observer: Observer<T>){
    observe(lifecycleOwner , object :Observer<T>{
        override fun onChanged(value: T) {
            removeObserver(this)
            observer.onChanged(value)
        }
    })
}


fun <T> ComponentActivity.collectLatestLifeCycleFlow(flow : Flow<T>, collect : suspend (T) -> Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(Lifecycle.State.STARTED){
            flow.collectLatest(collect)
        }
    }
}