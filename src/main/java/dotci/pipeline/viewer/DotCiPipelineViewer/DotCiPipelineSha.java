package dotci.pipeline.viewer.DotCiPipelineViewer;

import org.kohsuke.stapler.export.*;

import java.util.*;

@ExportedBean
public class DotCiPipelineSha {
    @Exported
    public String getSha() {
        return sha;
    }

    @Exported
    public List<DotCiStep> getSteps() {
        return steps;
    }

    public DotCiPipelineSha(String sha, List<DotCiStep> steps) {
        this.sha = sha;
        this.steps = steps;
    }

    String sha;
    List<DotCiStep>  steps;
}
