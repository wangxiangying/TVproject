package com.tools.tvproject

import android.app.Application
import android.content.Context

open class BaseApplication : Application() {
    companion object {
        lateinit var context: Context
        var errorMsg: String? = null
    }

    override fun onCreate() {
        super.onCreate()
        context = this
    }
}


