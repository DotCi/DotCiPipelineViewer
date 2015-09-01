package dotci.pipeline.viewer.DotCiPipelineViewer;

import com.google.common.collect.*;
import com.groupon.jenkins.*;
import com.groupon.jenkins.dynamic.build.*;
import com.groupon.jenkins.dynamic.build.repository.*;
import com.groupon.jenkins.dynamic.organizationcontainer.*;
import com.groupon.jenkins.util.*;
import com.mongodb.*;
import hudson.model.*;
import jenkins.model.*;
import org.kohsuke.stapler.*;
import org.kohsuke.stapler.export.*;

import javax.servlet.*;
import java.io.*;
import java.util.*;

import static com.google.common.collect.ImmutableMap.of;
import static java.util.Arrays.asList;

@ExportedBean
public class DotCiPipelineApi implements ModelObject {
    @Override
    public String getDisplayName() {
        return "Api";
    }
    public void doIndex(StaplerRequest req, StaplerResponse rsp) throws IOException, ServletException {
        String[] orgRepo = req.getParameter("repo").split("/");
        OrganizationContainer container = (OrganizationContainer) Jenkins.getActiveInstance().getItem(orgRepo[0]);
        DynamicProject project = container.getItem(orgRepo[1]);

        DynamicBuildRepository buildRepo = SetupConfig.get().getDynamicBuildRepository();
        Iterable<DynamicBuild> builds = buildRepo.getLast(project, 50, null, null);

        Map<String,List<DynamicBuild>> groupedBySha =  new HashMap<String, List<DynamicBuild>>();
        for(final DynamicBuild build : builds){
            String sha = build.getSha();
            if(groupedBySha.containsKey(sha)){
               groupedBySha.get(sha).add(build);
            }else{
               groupedBySha.put(sha, new ArrayList<DynamicBuild>(){{add(build);}}) ;
            }
        }
        ArrayList<DotCiPipelineSha> pipeLineShas = new ArrayList<DotCiPipelineSha>();
        for(String sha : groupedBySha.keySet()){
             pipeLineShas.add(new DotCiPipelineSha(sha,groupedBySha.get(sha)));
        }

        JsonResponse.render(req, rsp,new DotCiPipeline(pipeLineShas) );
    }
}
