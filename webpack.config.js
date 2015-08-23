module.exports = {
  entry: "./src/main/elm/app.js",
  output: {
    filename: "./src/main/webapp/js/app.js"
  },
  module:{
    loaders: [
      {
        test: /\.js?$/,
        exclude: /node_modules/,
        loader: 'babel-loader'
      },
      {
        test: /\.elm?$/,
        loader: 'elm-webpack-loader'
      }
    ]
  }
}
