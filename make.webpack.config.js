var autoprefixer = require('autoprefixer');
var precss      = require('precss');
module.exports = function(config){
  return  {
    entry: config.entry,
    output: config.output,
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
        },
        {
          test: /\.json?$/,
          loader: 'json-loader'
        },
        {
          test:   /\.css$/,
          loader: "style-loader!css-loader!postcss-loader"
        }
      ],
      postcss: function () {
        return [autoprefixer, precss];
      }
    }
  }
}
