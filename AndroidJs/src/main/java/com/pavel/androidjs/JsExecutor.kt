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

object JsExecutor {

    val jsonConverter = Json { ignoreUnknownKeys = true }

    fun loadJsBundle(context: Context, jsBundleFileName: String) {
        val script = context.assets.open(jsBundleFileName)
            .bufferedReader()
            .use {
                it.readText()
            }
        JsCore.loadJsScript(script)
    }

    fun loadPlainScript(script: String) {
        JsCore.loadJsScript(script)
    }

    fun execute(
        functionName: String,
        vararg params: Any?
    ): String {
        val function = "$functionName(${params.joinToString(separator = ",")})"
        return JsCore.executeJsFunction(function)
    }

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
