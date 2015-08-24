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
    public DotCiPipeline(){
        ArrayList<DotCiStep> steps = new ArrayList<DotCiStep>();
        steps.add(new DotCiStep("step1"));
        DotCiPipelineSha pipleline = new DotCiPipelineSha("sha", steps);
        this.shas = new ArrayList<DotCiPipelineSha>();
        shas.add(pipleline);
    }
}
