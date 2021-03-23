package com.example.weatherstation.util

import android.util.Log

object UtilLog {
    private val isDebug: Boolean = true
    private val TAG: String = "Ray"

    /**
     *d類型
     */
    fun d(msg: String) {
        if (isDebug) {
            Log.d(TAG, msg)
        }
    }

    /**
     *e類型
     */
    fun e(msg: String) {
        if (isDebug) {
            Log.e(TAG, msg)
        }
    }

    /**
     * v類型
     */
    fun v(msg: String) {
        if (isDebug) {
            Log.v(TAG, msg)
        }
    }
}