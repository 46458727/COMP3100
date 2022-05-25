import java.io.IOException;
import java.util.Iterator;

public class Scheduler {
    private static final String SCHD = "SCHD";
    private static Iterator<Server> iterator;

    public Scheduler(Iterator<Server> iterType) {
        iterator = iterType;
    }

    public void scheduleJob(ConnectionManager conMan, ServerManager sManager, Job j) throws IOException { 
        //asks server to schedule the given job on the server decided by the iterator.
        conMan.serverMsg(String.format("%s %d %s\n", SCHD, j.getJobID(), iterator.next().getServerName()));
    }
}
