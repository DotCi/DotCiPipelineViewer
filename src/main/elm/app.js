import Elm from './App.elm'
window.onload = function(){
  Elm.embed(Elm.App, document.getElementById('main'), {repo: window.repo});  
}
