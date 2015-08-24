package dotci.pipeline.viewer.DotCiPipelineViewer;

import com.groupon.jenkins.util.*;
import hudson.*;
import hudson.model.*;
import org.kohsuke.stapler.*;

import javax.servlet.*;
import java.io.*;
import java.net.*;

@Extension
public class DotCiPipelineView implements RootAction {
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
    public DotCiPipelineApi getApi() {
        return new DotCiPipelineApi();
    }
}

