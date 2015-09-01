package dotci.pipeline.viewer.DotCiPipelineViewer;

import com.google.common.base.*;
import com.google.common.collect.*;
import com.groupon.jenkins.dynamic.build.*;
import com.groupon.jenkins.dynamic.build.api.*;
import com.groupon.jenkins.dynamic.build.cause.*;
import hudson.model.*;
import org.kohsuke.stapler.export.*;

import javax.annotation.*;
import java.util.*;

@ExportedBean
public class DotCiPipelineSha {

    private final BuildCause.CommitInfo commit;
    private String sha;
    private List<DotCiStep>  steps;
    public DotCiPipelineSha(String sha, List<DynamicBuild> dynamicBuilds) {
        this.sha = sha;
        ImmutableListMultimap<String, DynamicBuild> byStep = Multimaps.index(dynamicBuilds, new Function<DynamicBuild, String>() {
            public String apply(DynamicBuild dynamicBuild) {
                ParametersAction paramAction = dynamicBuild.getAction(ParametersAction.class);
                return (String) paramAction.getParameter("DOTCI_STEP").getValue();
            }
        });

        this.commit = new ProcessedBuild(dynamicBuilds.get(0)).getCommit();
        steps = new ArrayList<DotCiStep>();
        for(String step : byStep.keySet()){
          steps.add(new DotCiStep(step,byStep.get(step)));
        }
    }

    @Exported
    public String getSha() {
        return sha;
    }

    @Exported
    public List<DotCiStep> getSteps() {
        return steps;
    }

    @Exported
    public BuildCause.CommitInfo getCommit() {
        return commit;
    }


}
