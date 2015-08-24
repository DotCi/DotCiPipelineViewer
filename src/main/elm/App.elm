module App where 
import Html exposing (..)
import Json.Decode as Json exposing((:=))
import Http
import Signal exposing (..)
import Task exposing (..)

main = 
  Signal.map view  pipeLineMailBox.signal

--View 
view pipeLineResult = 
    case  pipeLineResult of 
      Err _ ->  h1 [] [text "error"]
      Ok pipeLine ->  h1 [] 
                 ( List.map(\pipeLineSha -> text pipeLineSha.sha) pipeLine)




--Ports
port repo: String

port retrivePipelinePort: Task Http.Error ()
port retrivePipelinePort =  
  retrivePipeline
  |> (\task -> Task.toResult(task) `andThen` Signal.send pipeLineMailBox.address)

--Models
type alias PipelineStep = { name: String }
type alias PipelineSha = {sha: String, steps: List PipelineStep}

--Mailbox

pipeLineMailBox: Mailbox (Result Http.Error  (List PipelineSha))
pipeLineMailBox = 
   Signal.mailbox  (Ok [])

--Api
retrivePipeline: Task Http.Error (List PipelineSha)
retrivePipeline = 
  let 
      pipelineStep = Json.object1 PipelineStep  ("name" := Json.string)
      pipelineSha = 
        Json.object2 PipelineSha
          ("sha" := Json.string)
          ("steps" := Json.list pipelineStep)

      pipelineDecoder = ("shas" := Json.list pipelineSha)
  in 
      Http.get pipelineDecoder "/jenkins/dotciPipeline/api/?tree=*,shas[*,steps[*]]" 
