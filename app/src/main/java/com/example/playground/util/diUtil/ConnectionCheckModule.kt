package com.example.playground.util.diUtil

import android.content.Context
import android.net.ConnectivityManager
import dagger.Module
import dagger.Provides


import android.net.NetworkInfo
import android.util.Log
import com.example.playground.di.modules.AppModule
import java.util.Objects
import javax.inject.Named

@Module(includes = [AppModule::class])
class ConnectionCheckModule {

    @Named("isOnline")
    @Provides
    fun isOnline(@Named("AppContext")context: Context): Boolean {
        val connectivityManager: ConnectivityManager
        var networkInfo: NetworkInfo? = null

        try {
            connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            networkInfo = Objects.requireNonNull(connectivityManager).activeNetworkInfo
        } catch (e: Exception) {
            Log.e("connectivity", e.toString())
        }

        return networkInfo != null && networkInfo.isConnected

    }

}
