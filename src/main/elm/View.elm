module View(view) where
import Html exposing (..)
import Html.Attributes exposing (..)
view pipeLineResult = 
    case  pipeLineResult of 
      Err _ ->  h1 [] [text "error"]
      Ok pipeLine ->  div []
                 ( List.map(\pipeLineSha -> pipeLineShaView pipeLineSha) pipeLine)

pipeLineShaView pipelineSha = 
  div [class "fieldset"]
      [h1 []
           [span [] [text pipelineSha.sha]],
       div [class "builds"]
            ( List.map(\buildStep -> buildStepView buildStep) pipelineSha.steps)
       ]
buildStepView buildStep = 
    text "blah"
