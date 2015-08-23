package dotci.pipeline.viewer.DotCiPipelineViewer;

import hudson.*;
import hudson.model.*;

@Extension
public class DotCiPipeline implements RootAction {
    @Override
    public String getIconFileName() {
        return null;
    }

    @Override
    public String getDisplayName() {
        return "DotCi Pipeline Viewer";
    }

    @Override
    public String getUrlName() {
        return "dotciPipeline";
    }
}
