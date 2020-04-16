package com.example.adtcodingassesment

import android.app.Application
import com.example.adtcodingassesment.model.network.ConnectivityInspector

/**
 * an application class that provide application instace
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    /**
     * describe setting the listener for connectivity inspector class
     */
    fun setConnectivityListener(listener: ConnectivityInspector.ConnectivityReceiverListener?) {
        ConnectivityInspector.connectivityReceiverListener = listener
    }

    companion object {
        @get:Synchronized
        var instance: App? = null
            private set
    }
}