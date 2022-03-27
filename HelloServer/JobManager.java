import java.io.IOException;
import java.util.HashMap;


public class JobManager {
    private static HashMap<Integer, Job> jobL;
    private static final String REDY = "REDY\n", JOBN = "JOBN", NONE = "NONE", JOBP = "JOBP", RESF = "RESF", RESR = "RESR", JCPL = "JCPL";
    private boolean jobsLeft = true;
    private Scheduler jobScheduler;
    private Integer jobNumber;
    
    public JobManager() throws IOException{
        jobL = new HashMap<Integer, Job>();
        jobNumber = 0;
    }

    public void getJob(ConnectionManager cManager, ServerManager sManager) throws IOException {
        String[] jobReqResponse = cManager.serverMsg(REDY).split(" ");
        switch (jobReqResponse[0]) {
            case NONE:
                jobsLeft = false;
                break;

            case JOBP:
            case JOBN:
                addJob(new Job(jobReqResponse));
                scheduleJob(cManager, sManager);
                break;
            
            case RESR:
            case RESF:
                sManager.serverStatusUpdate(jobReqResponse);
                break;

            case JCPL:
                jobComplete(jobReqResponse);
                break;
        }
    }

    public ServerManager handshakeJob(ConnectionManager cManager) throws IOException {
        addJob(new Job(cManager.serverMsg(REDY).split(" ")));
        ServerManager sManager = new ServerManager(cManager);
        jobScheduler = new Scheduler(new LRRIterator(ServerManager.serverL).iterator());
        scheduleJob(cManager, sManager);
        return sManager;
    }

    public void addJob(Job newJob) {
        jobL.put(newJob.jobID, newJob);
    }

    public void scheduleJob(ConnectionManager cManager, ServerManager sManager) throws IOException {
        jobScheduler.scheduleJob(cManager, sManager, jobL.get(jobNumber++));
    }

    public void jobComplete(String[] jInfo) {
        jobL.get(Integer.parseInt(jInfo[2])).status = "COMPLETE";
    }

    public HashMap<Integer, Job> getJobList(){
        return jobL;
    }

    public void removeJob(Job job) {
        jobL.remove(job.jobID);
    }

    public boolean jobAvaliable(){
        return jobsLeft;
    }
}
