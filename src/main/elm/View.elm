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
           [span [] [ commitLink pipelineSha.commit]],
       div []
            ( List.map(\buildStep -> buildStepView buildStep) pipelineSha.steps)
       ]
buildStepView buildStep = 
  div [class "fieldset"]
      [h1 []
           [span [] [text buildStep.name]],
       div []
            (buildStep.builds |> List.map(\build-> buildView build))
       ]
buildView build = 
   text (toString build.number)

commitLink commit  =
   a [ href commit.commitUrl] [ text (commit.message ++ " ( " ++ commit.shortSha ++  ")")]


