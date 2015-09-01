
module.exports = require('./make.webpack.config.js')(
  {
    entry:{
      app:  [
        'webpack/hot/only-dev-server',
        "./src/main/elm/app.js"
      ]},
      output: {
        filename: "app.js",
        publicPath: "http://localhost:3000/assets/"
      }
  }
)
