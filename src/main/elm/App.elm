module App where 
import Json.Decode as Json exposing((:=))
import Http
import Signal exposing (..)
import View exposing (view)
import Task exposing (..)

main = 
  Signal.map (view rootURL)  pipeLineMailBox.signal
--Ports
port repo: String
port rootURL: String

port retrivePipelinePort: Task Http.Error ()
port retrivePipelinePort =  
  retrivePipeline
  |> (\task -> Task.toResult(task) `andThen` Signal.send pipeLineMailBox.address)

--Models
type alias PipelineSha = {sha: String, steps: List PipelineStep, commit: Commit}
type alias Commit = {avatarUrl: String, branch: String, commitUrl: String, committerName: String, message: String, shortSha: String}
type alias PipelineStep = { name: String, builds: List Build }
type alias Build = {number: Int, cancelUrl: String,displayTime: String, result: String}

--Mailbox

pipeLineMailBox: Mailbox (Result Http.Error  (List PipelineSha))
pipeLineMailBox = 
     Signal.mailbox  (Ok [])

--Api
retrivePipeline: Task Http.Error (List PipelineSha)
retrivePipeline = 
  let 
      buildDecoder = Json.object4 Build 
                                ("number" := Json.int)
                                ("cancelUrl" := Json.string)
                                ("displayTime" := Json.string)
                                ("result" := Json.string)
      pipelineStepDecoder = Json.object2 PipelineStep  
                                        ("name" := Json.string)
                                        ("builds" := Json.list buildDecoder)
      commitDecoder= Json.object6 Commit 
                         ("avatarUrl" := Json.string) 
                         ("branch" := Json.string) 
                         ("commitUrl" := Json.string) 
                         ("committerName" := Json.string) 
                         ("message" := Json.string) 
                         ("shortSha" := Json.string) 
      pipelineShaDecoder = 
        Json.object3 PipelineSha
          ("sha" := Json.string)
          ("steps" := Json.list pipelineStepDecoder)
          ("commit" := commitDecoder)

      pipelineDecoder = ("shas" := Json.list pipelineShaDecoder)
  in 
      Http.get pipelineDecoder ("/jenkins/dotciPipeline/api/?tree=*,shas[*,commit[*],steps[*,builds[*]]]&repo="++repo)
