package com.mtheus.pokedex.app

import android.app.Application
import android.content.Context

class MyAplication : Application() {
    override fun onCreate() {
        instance = this

        super.onCreate()
    }

    companion object {
        var instance: MyAplication? = null
            private set

        val context: Context?
            get() = instance
    }


}