package com.example.wincompose

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    var counter = MutableLiveData(0)

    var incrementCounter = {
        counter.value = counter.value?.plus(1)
    }

    var decrementCounter = {
        counter.value = counter.value?.minus(1)
    }
}