package com.example.playground.repository

import android.os.Handler
import com.example.playground.network.PostApi

class ProjectRepository {

    // Function to return data to viewmodel
    fun refreshData(onDataReadyCallback: OnDataReadyCallback) {
        Handler().postDelayed({ onDataReadyCallback.onDataReady("new data") },2000)
    }



}

interface OnDataReadyCallback {
    fun onDataReady(data : String)
}