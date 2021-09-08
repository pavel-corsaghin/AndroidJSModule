package com.pavel.androidjs

/**
 * Created by Hung Nguyen on 2021/09/08.
 * Ping me at hungnm.k52tnvlkt@gmail.com
 * Happy coding ^_^
 */

/**
 * Utility class that load native library and execute native methods
 */
object JsCore {
    /**
     * Load the native library
     */
    init {
        try {
            System.loadLibrary("core")
        } catch (e: UnsatisfiedLinkError) {
        }
    }

    /**
     * The link to native function from library to load javascript code
     * @param script The script will be loaded
     */
    external fun loadJsScript(script: String)

    /**
     * The link to native function from library for executing javascript function
     * @param function The javascript function
     */
    external fun executeJsFunction(function: String): String
}