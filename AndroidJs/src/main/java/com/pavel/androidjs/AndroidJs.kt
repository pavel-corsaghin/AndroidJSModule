package com.pavel.androidjs

import android.content.Context

/**
 * Created by Hung Nguyen on 2021/09/04.
 * Ping me at hungnm.k52tnvlkt@gmail.com
 * Happy coding ^_^
 */

object AndroidJs {

    /**
     * Load the native library
     */
    init {
        try {
            System.loadLibrary("core")
        } catch (e: UnsatisfiedLinkError) {
        }
    }

    fun loadJsBundle(context: Context, jsBundleFileName: String) {
        val script = context.assets.open(jsBundleFileName)
            .bufferedReader()
            .use {
                it.readText()
            }
        loadJsScript(script)
    }

    fun executeJsFunction(
        functionName: String,
        vararg params: Any?
    ): String {
        val function = "$functionName(${params.joinToString(separator = ",")})"
        return executeJSFunction(function)
    }

    /**
     * The link to native function from library to load javascript code
     * @param script The script will be loaded
     */
    private external fun loadJsScript(script: String)

    /**
     * The link to native function from library for executing javascript function
     * @param function The javascript function
     */
    private external fun executeJSFunction(function: String): String

}