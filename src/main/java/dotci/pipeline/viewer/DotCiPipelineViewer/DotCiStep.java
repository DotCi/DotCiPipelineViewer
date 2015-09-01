package dotci.pipeline.viewer.DotCiPipelineViewer;

import com.groupon.jenkins.dynamic.build.*;
import com.groupon.jenkins.dynamic.build.api.*;
import com.groupon.jenkins.dynamic.build.cause.*;
import org.kohsuke.stapler.export.*;

import java.util.*;

@ExportedBean
public class DotCiStep {

     private final ArrayList<ProcessedBuild> builds;

     public DotCiStep(String name,List<DynamicBuild> builds) {
          this.name = name;
          this.builds = new ArrayList<ProcessedBuild>();
          for(DynamicBuild build : builds){
               this.builds.add(new ProcessedBuild(build));
          }
     }
      @Exported
     public String getName() {
          return name;
     }

     @Exported
     public ArrayList<ProcessedBuild> getBuilds() {
          return builds;
     }

     private String name;
}
