package com.training.ecommercetrainingproject

import android.app.Application
import android.content.Context
import android.nfc.Tag
import android.util.Log
import com.github.pwittchen.reactivenetwork.library.rx2.ReactiveNetwork
import com.google.firebase.crashlytics.FirebaseCrashlytics
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

// manage the application lifecycle
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        listenToNetworkConnectivity()
    }

    private fun listenToNetworkConnectivity(){
        ReactiveNetwork
            .observeInternetConnectivity()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{isConnected: Boolean ->
                Log.d(TAG, "Connected to internet: $isConnected")

                FirebaseCrashlytics
                    .getInstance()
                    .setCustomKey("connnected_to_internet", isConnected)
            }

    }
    companion object{
        private const val TAG = "MyApplication"
    }
}