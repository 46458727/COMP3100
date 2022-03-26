import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JobManager {
    private static List<Job> jobL;
    private static final String REDY = "REDY\n", JOBN = "JOBN", NONE = "NONE";
    private boolean jobsLeft = true;
    
    public JobManager() throws IOException{
        jobL = new ArrayList<Job>();
    }

    public void getJob(ConnectionManager cManager) throws IOException {
        String[] jobReqResponse = cManager.serverMsg(REDY).split(" ");
        switch (jobReqResponse[0]) {
            case NONE:
                jobsLeft = false;
                break;
            case JOBN:
                jobL.add(new Job(jobReqResponse));
                break;
        }
        
    }

    public List<Job> getJobList(){
        return jobL;
    }

    public void addJob(Job job) {
        jobL.add(job);
    }

    public void removeJob(Job job) {
        jobL.remove(job);
    }

    public boolean jobAvaliable(){
        return jobsLeft;
    }
}
