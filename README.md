# AndroidJSModule

A simple library that helps Android developers to execute JavaScript code from Android native side easily without using Webview.

## Installation

Will distribute library via maven central repository and provide package soon

## Usage

- Copy your javascript bundle file to Android's assets folder
- Load js bundle file and execute any function from it as below:


```sh
// Load js bundle file
AndroidJs.loadJsBundle(this, "JsModule/dist/jsBundle.js")

// Get simple message
val message = AndroidJs.executeJsFunction("JsModule.getMessage") // This is message from Javascript side

// Execute function and get result
val log  = AndroidJs.executeJsFunction("JsModule.MathJs.log", 10000, 10) // 4
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
