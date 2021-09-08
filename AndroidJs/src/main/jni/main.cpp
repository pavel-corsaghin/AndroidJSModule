//
// Created by Hung Nguyen on 2021/09/04.
// Ping me at hungnm.k52tnvlkt@gmail.com
// Happy coding ^_^
//

#include <jni.h>
#include <string>
#include <JavaScriptCore/JavaScriptCore.h>

std::string JSStringToStdString(JSStringRef jsString) {
    size_t maxBufferSize = JSStringGetMaximumUTF8CStringSize(jsString);
    char *utf8Buffer = new char[maxBufferSize];
    size_t bytesWritten = JSStringGetUTF8CString(jsString, utf8Buffer, maxBufferSize);
    std::string utf_string = std::string(utf8Buffer, bytesWritten - 1);
    delete[] utf8Buffer;
    return utf_string;
}

JSGlobalContextRef globalContext = nullptr;

extern "C"
JNIEXPORT void JNICALL
Java_com_pavel_androidjs_JsCore_loadJsScript(JNIEnv *env, jobject thiz, jstring script) {
    JSContextGroupRef contextGroup = JSContextGroupCreate();
    globalContext = JSGlobalContextCreateInGroup(contextGroup, nullptr);
    const char *cString = env->GetStringUTFChars(script, 0);
    JSStringRef statement = JSStringCreateWithUTF8CString(cString);
    JSEvaluateScript(globalContext, statement, nullptr, nullptr, 0, nullptr);
    JSContextGroupRelease(contextGroup);
    JSStringRelease(statement);
}

extern "C"
JNIEXPORT jstring JNICALL
Java_com_pavel_androidjs_JsCore_executeJsFunction(JNIEnv *env, jobject thiz, jstring function) {
    JSStringRef fName = JSStringCreateWithUTF8CString(env->GetStringUTFChars(function, nullptr));
    JSValueRef retValue = JSEvaluateScript(globalContext, fName, nullptr, nullptr, 0, nullptr);
    JSStringRef retString = JSValueToStringCopy(globalContext, retValue, nullptr);
    std::string hello = JSStringToStdString(retString);

    return env->NewStringUTF(hello.c_str());
}