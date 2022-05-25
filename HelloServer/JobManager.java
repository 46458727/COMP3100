import java.io.IOException;
import java.util.Iterator;

public class JobManager {
    // String Commands
    private static final String REDY = "REDY\n", JOBN = "JOBN", NONE = "NONE", JOBP = "JOBP", RESF = "RESF",
            RESR = "RESR", JCPL = "JCPL", OK = "OK";
    // Are there any jobs left?
    private boolean jobsLeft = true;
    // JobManager stores the scheduler
    private Scheduler jobScheduler;
    // Current job
    public static Job curJob;

    public void getJob(ConnectionManager cManager, ServerManager sManager) throws IOException {
        // break the job up up
        String[] jobReqResponse = cManager.serverMsg(REDY).split(" ");
        switch (jobReqResponse[0]) {
            case NONE:
                // if no jobs left must know
                jobsLeft = false;
                break;

            case JOBP:
            case JOBN:
                // if a new job or a job that failed previously needs to be resubmitted shedule
                // it and record its existence
                addJob(new Job(jobReqResponse));
                scheduleJob(cManager, sManager);
                break;

            case RESR:
            case RESF:
                // if server failure update server status to stop job assigning
                sManager.serverStatusUpdate(jobReqResponse);
                break;

            case JCPL:
                // record job completion
                jobComplete(jobReqResponse);
                break;
            
            case OK:
                break;

            default:
                // if job is unk exit as it would be unaccounted for
                System.out.println(String.format("Error Job %s unknown", jobReqResponse[0]));
                System.exit(-1);
        }
    }

    public ServerManager handshakeJob(ConnectionManager cManager, String[] args) throws IOException {
        // Handshake process is a unique job request which sets up everthing as such it
        // is handled seperately
        addJob(new Job(cManager.serverMsg(REDY).split(" ")));
        // initialises server list to be used for rest of assigning
        ServerManager sManager = new ServerManager(cManager);
            Iterator<Server>  sIter;
        switch (args[1].toLowerCase()) {
            case "lrr":
                sIter = new LRRIterator(ServerManager.serverL).iterator();
                break;
            case "fc":
                sIter = new FCIterator(cManager, this).iterator();
                break;
            case "bfc":
                sIter = new BFButBetterIterator(cManager, this).iterator();
                break;
            default:
                System.out.println("Please enter a valid algo (FC, BF, LRR)");
                sIter = new BFButBetterIterator(cManager, this).iterator();
                System.exit(-1);
        }

        jobScheduler = new Scheduler(sIter);
        // schedules the job
        scheduleJob(cManager, sManager);
        return sManager;
    }

    public void addJob(Job newJob) {
        curJob = newJob;
    }

    public void scheduleJob(ConnectionManager cManager, ServerManager sManager) throws IOException {
        // schedules the current job
        jobScheduler.scheduleJob(cManager, sManager, curJob);
    }

    public void jobComplete(String[] jInfo) {
        // update job status
        curJob.setStatus("COMPLETE");
    }

    public boolean jobAvaliable() {
        // check if there are still jobs
        return jobsLeft;
    }
}
