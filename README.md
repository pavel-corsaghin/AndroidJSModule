# AndroidJSModule
![Maven Central](https://img.shields.io/maven-central/v/io.github.pavel-corsaghin/androidjs.svg)

A simple library that helps Android developers to execute JavaScript code from Android native side easily without using Webview.

## Installation

The easiest way to add the library to your project is by adding it as a dependency to your `build.gradle`
```groovy
dependencies {
    implementation "io.github.pavel-corsaghin:androidjs:${release_version}""
}
```

## Usage

- Usage library with plain javascript code:

```groovy
// Load plain script
val plainScript = "var js_obj = {add: function(a,b) { return a+b; }, multiply: function(a,b) { return a*b; }};"
JsExecutor.loadPlainScript(plainScript)

// Execute function and get result
val add = JsExecutor.execute("js_obj.add", 1, 2) //3

val multiply = JsExecutor.execute("js_obj.multiply", 4, 5) //20
```

- Load a js bundle and execute functions

```groovy
// Load js bundle file
JsExecutor.loadJsBundle(this, "JsModule/dist/jsBundle.js")

// Get simple message
val message = JsExecutor.execute("JsModule.getMessage") //This is message from Javascript side

// Execute function and get result
val logStr = JsExecutor.execute("JsModule.MathJs.log", 10000, 10) //4
```

- Get custom kotlin object

```groovy
// Primitive types
val logDouble = JsExecutor.executeToGetObject<Double>("JsModule.MathJs.log", 1000, 10) //2.999

// Custom object
val inputPoint = Point(3.4, 5.2)
val outputPoint = JsExecutor.executeToGetObject<Point>(
    "JsModule.MathJs.doublePoint",
     JsExecutor.jsonConverter.encodeToString(inputPoint)
)

// Define Point class
@Serializable
data class Point(val lat: Double, val lon: Double)
```

## Contributing

All contributions are welcomed. Please create your PR and send to me.

## License

MIT
