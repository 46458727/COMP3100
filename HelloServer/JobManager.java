import java.io.IOException;


public class JobManager {
   
    // Are there any jobs left?
    private boolean jobsLeft = true;
    // JobManager stores the scheduler
    private Scheduler jobScheduler;
    // Current job
    public static Job curJob;

    CommandExecution cExecution;

    JobManager(CommandExecution commandExecution, Scheduler scheduler){
        cExecution = commandExecution;
        jobScheduler = scheduler;
    }

    public void getJob() throws IOException {
        // break the job up up
        String[] jobReqResponse = cExecution.REDY().split(" ");
        Responses r = Responses.valueOf(jobReqResponse[0]);
        switch (r) {
            case NONE: 
                // if no jobs left must know
                jobsLeft = false;
                break;

            case JOBP:
            case JOBN:
                // if a new job or a job that failed previously needs to be resubmitted shedule
                // it and record its existence
                curJob = new Job(jobReqResponse);
                jobScheduler.scheduleJob(cExecution,curJob);
                break;

            case RESR:
            case RESF:
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

    public void jobComplete(String[] jInfo) {
        // update job status
        curJob.setStatus("COMPLETE");
    }

    public boolean jobAvaliable() {
        // check if there are still jobs
        return jobsLeft;
    }
}
