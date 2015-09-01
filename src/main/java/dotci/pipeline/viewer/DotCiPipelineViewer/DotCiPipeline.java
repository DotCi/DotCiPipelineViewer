package dotci.pipeline.viewer.DotCiPipelineViewer;

import org.kohsuke.stapler.export.*;

import java.util.*;

@ExportedBean
public class DotCiPipeline {

    @Exported
    public List<DotCiPipelineSha> getShas() {
        return shas;
    }

    List<DotCiPipelineSha> shas;
    public DotCiPipeline(List<DotCiPipelineSha> shas){
        this.shas = shas;
    }
}
