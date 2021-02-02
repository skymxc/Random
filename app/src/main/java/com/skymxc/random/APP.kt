package com.skymxc.random

import android.app.Application
import android.content.Context

/**
 * <p>
 *
 * </p>
 *
 * @author 孟祥超
 * <p>
 * date: 2021/2/1
 */
class APP : Application() {
    companion object {
        lateinit var applicationContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        APP.applicationContext = this.applicationContext
    }
}