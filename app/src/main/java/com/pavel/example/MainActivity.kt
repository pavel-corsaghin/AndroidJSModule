package com.pavel.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.pavel.androidjs.JsExecutor
import com.pavel.androidjs.JsExecutor.jsonConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.encodeToString

class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "MainActivity"
        const val JS_BUNDLE_FILE = "JsModule/dist/jsBundle.js"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Examples
        plainJsScriptExample()
        jsBundleExample()
        executeToGetObjectExample()
    }

    /**
     * The example shows how to use library with plain javascript code
     */
    private fun plainJsScriptExample() {
        // Load plain script
        val plainScript =
            "var js_obj = {add: function(a,b) { return a+b; }, multiply: function(a,b) { return a*b; }};"
        JsExecutor.loadPlainScript(plainScript)

        // Execute function and get result
        val add = JsExecutor.execute("js_obj.add", 1, 2)
        Log.e(TAG, add)
        val multiply = JsExecutor.execute("js_obj.multiply", 4, 5)
        Log.e(TAG, multiply)
    }

    /**
     * The example shows how to use library to load a js bundle and execute it's function
     */
    private fun jsBundleExample() {
        // Load js bundle file
        JsExecutor.loadJsBundle(this, "JsModule/dist/jsBundle.js")

        // Get simple message
        val message = JsExecutor.execute("JsModule.getMessage")
        Log.e(TAG, message)

        // Execute function and get result
        val logStr = JsExecutor.execute("JsModule.MathJs.log", 10000, 10)
        Log.e(TAG, logStr)
    }

    /**
     * The advanced example shows how to use library to get executed kotlin object
     * Our library using kotlinx serializable to encode/decode object
     * Please make sure you have added @Serializable annotation to result class
     */
    private fun executeToGetObjectExample() {
        // Load js bundle file
        JsExecutor.loadJsBundle(this, "JsModule/dist/jsBundle.js")

        // Primitive types
        val logDouble = JsExecutor.executeToGetObject<Double>("JsModule.MathJs.log", 1000, 10)
        Log.e(TAG, logDouble.toString())

        // Custom object
        val inputPoint = Point(3.4, 5.2)
        val outputPoint = JsExecutor.executeToGetObject<Point>(
            "JsModule.MathJs.doublePoint",
            jsonConverter.encodeToString(inputPoint)
        )
        Log.e(TAG, outputPoint.toString())
    }
}

@Serializable
data class Point(val lat: Double, val lon: Double)