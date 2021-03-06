import java.io.IOException;
import java.util.Iterator;

public class Scheduler {
    private static Iterator<Server> iterator;

    public Scheduler(String algo, CommandExecution cExecution) throws IOException {
        //switch to figure out which algo the user selected
        switch (algo.toLowerCase()) {
            case "lrr":
                iterator = new LRRIterator(cExecution).iterator();
                break;
            case "fc":
                iterator = new FCIterator(cExecution).iterator();
                break;
            case "cum":
                iterator = new CUMIterator(cExecution).iterator();
                break;
            default:
                //if the entered case doesnt match any of the cases give the user the avail cases and exit
                System.out.println("Please enter a valid algo (FC, CUM, LRR)");
                iterator = new CUMIterator(cExecution).iterator();
                System.exit(-1);
        }
    }

    public void scheduleJob(CommandExecution cExecution, Job j) throws IOException { 
        //asks server to schedule the given job on the server decided by the iterator.
        cExecution.SCHD(j.getJobID(), iterator.next().getServerName());
    }
}
