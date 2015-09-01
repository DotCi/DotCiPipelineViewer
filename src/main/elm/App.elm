module App where 
import Json.Decode as Json exposing((:=))
import Http
import Signal exposing (..)
import View exposing (view)
import Task exposing (..)

main = 
  Signal.map view  pipeLineMailBox.signal
--Ports
port repo: String

port retrivePipelinePort: Task Http.Error ()
port retrivePipelinePort =  
  retrivePipeline
  |> (\task -> Task.toResult(task) `andThen` Signal.send pipeLineMailBox.address)

--Models
type alias PipelineStep = { name: String, builds: List Build }
type alias PipelineSha = {sha: String, steps: List PipelineStep}
type alias Build = {number: Int}

--Mailbox

pipeLineMailBox: Mailbox (Result Http.Error  (List PipelineSha))
pipeLineMailBox = 
     Signal.mailbox  (Ok [])

--Api
retrivePipeline: Task Http.Error (List PipelineSha)
retrivePipeline = 
  let 
      buildDecoder = Json.object1 Build ("number" := Json.int)
      pipelineStep = Json.object2 PipelineStep  ("name" := Json.string)  ("builds" := Json.list buildDecoder)
      pipelineSha = 
        Json.object2 PipelineSha
          ("sha" := Json.string)
          ("steps" := Json.list pipelineStep)

      pipelineDecoder = ("shas" := Json.list pipelineSha)
  in 
      Http.get pipelineDecoder ("/jenkins/dotciPipeline/api/?tree=*,shas[*,steps[*,builds[*]]]&repo="++repo)
