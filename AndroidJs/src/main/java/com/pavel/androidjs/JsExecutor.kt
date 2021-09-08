package com.pavel.androidjs

import android.content.Context
import android.util.Log
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.lang.Exception

/**
 * Created by Hung Nguyen on 2021/09/04.
 * Ping me at hungnm.k52tnvlkt@gmail.com
 * Happy coding ^_^
 */

/**
 * Utility class that helps to load/execute javascript code
 */
object JsExecutor {

    val jsonConverter = Json { ignoreUnknownKeys = true }

    /**
     * Method loads javascript bundle file from assets folder.
     * The script will be cached until we load another file/script
     * @param context: The Context for open file
     * @param jsBundleFileName: The file name, should be in format path/to/your/jsBundle.js
     */
    fun loadJsBundle(context: Context, jsBundleFileName: String) {
        val script = context.assets.open(jsBundleFileName)
            .bufferedReader()
            .use {
                it.readText()
            }
        JsCore.loadJsScript(script)
    }

    /**
     * Method loads javascript bundle file from javascript plain string.
     * The script will be cached until we load another file/script
     * @param script: The javascript code
     */
    fun loadPlainScript(script: String) {
        JsCore.loadJsScript(script)
    }

    /**
     * Method executes javascript function with parameters
     * @param functionName: The function name, should be in format path.to.your.function
     * @param params: should be primitive types or json string
     * @return the result of javascript function in String
     */
    fun execute(
        functionName: String,
        vararg params: Any?
    ): String {
        val function = "$functionName(${params.joinToString(separator = ",")})"
        return JsCore.executeJsFunction(function)
    }

    /**
     * Method executes javascript function with parameters
     * @param functionName: The function name, should be in format path.to.your.function
     * @param params: should be primitive types or json string
     * @return the result of javascript function in provided generic type
     */
    inline fun <reified T> executeToGetObject(
        functionName: String,
        vararg params: Any?
    ): T {
        val function = "$functionName(${params.joinToString(separator = ",")})"
        val resultString = JsCore.executeJsFunction(function)

        return try {
            jsonConverter.decodeFromString(resultString)
        } catch (e: Exception) {
            if (e is SerializationException) {
                Log.e(
                    "JsExecutor",
                    "Please make sure you have added @Serializable annotation to T class"
                )
            }
            throw e
        }
    }
}
