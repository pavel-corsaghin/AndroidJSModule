package com.pavel.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pavel.androidjs.AndroidJs

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Load js bundle file
        AndroidJs.loadJsBundle(this, "JsModule/dist/jsBundle.js")

        // Get simple message
        val message = AndroidJs.executeJsFunction("JsModule.getMessage")
        Log.e(TAG, message)

        // Execute function and get result
        val log  = AndroidJs.executeJsFunction("JsModule.MathJs.log", 10000, 10)
        Log.e(TAG, log)
    }
}