package dotci.pipeline.viewer.DotCiPipelineViewer;

import org.kohsuke.stapler.export.*;

@ExportedBean
public class DotCiStep {
     public DotCiStep(String name) {
          this.name = name;
     }
      @Exported
     public String getName() {
          return name;
     }

     private String name;
}
