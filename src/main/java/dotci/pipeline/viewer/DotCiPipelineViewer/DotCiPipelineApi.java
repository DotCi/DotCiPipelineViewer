package dotci.pipeline.viewer.DotCiPipelineViewer;

import com.groupon.jenkins.util.*;
import hudson.model.*;
import org.kohsuke.stapler.*;
import org.kohsuke.stapler.export.*;

import javax.servlet.*;
import java.io.*;
import java.util.*;

@ExportedBean
public class DotCiPipelineApi implements ModelObject {
    @Override
    public String getDisplayName() {
        return "Api";
    }
    public void doIndex(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {

        JsonResponse.render(req, rsp,new DotCiPipeline() );
    }
}
