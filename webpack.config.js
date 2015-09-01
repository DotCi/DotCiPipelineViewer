
module.exports = require('./make.webpack.config.js')(
  {
    entry: "./src/main/elm/app.js",
    output: {
      filename: "./src/main/webapp/js/app.js"
    }
  }
)
