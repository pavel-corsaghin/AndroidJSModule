var path = require('path')

module.exports = {
    entry: { JsModule: "./index.js" },
    output: {
        path:  path.resolve(__dirname, 'dist'),
        filename: "jsBundle.js",
        library: "[name]",
        libraryTarget: "var"
    }
};
