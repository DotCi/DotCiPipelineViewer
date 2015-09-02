import Elm from './App.elm'
require('./app.css')

window.onload = function(){
  Elm.embed(Elm.App, document.getElementById('main'), {repo: window.repo,rootURL: window.rootURL});  
}
