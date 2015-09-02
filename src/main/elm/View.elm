module View(view) where
import Html exposing (..)
import Regex exposing (..)
import Html.Attributes exposing (..)
view rootURL pipeLineResult = 
    case  pipeLineResult of 
      Err _ ->  h1 [] [text "error"]
      Ok pipeLine ->  div []
                 ( List.map(\pipeLineSha -> pipeLineShaView rootURL pipeLineSha) pipeLine)

pipeLineShaView rootURL pipelineSha = 
  div [class "fieldset"]
      [h1 []
           [span [] [ commitLink pipelineSha.commit]],
       div []
            ( List.map(\buildStep -> buildStepView rootURL buildStep) pipelineSha.steps)
       ]
buildStepView rootURL buildStep = 
  div [class "fieldset"]
      [h1 []
           [span [] [text buildStep.name]],
       div []
            (buildStep.builds |> List.map(\build-> buildView rootURL build))
       ]
buildView rootURL build = 
   a [class build.result, href (buildUrl rootURL build)][
   text ("#" ++ (toString build.number) ++ " - " ++ build.result ++ " - " ++ build.displayTime )]

commitLink commit  =
   a [ href commit.commitUrl] [ text (commit.message ++ " ( " ++ commit.shortSha ++  ")")]

buildUrl rootURL build =
    rootURL ++"/"++ (replace All (regex "/stop") (\_ -> "") build.cancelUrl)

