import java.io.IOException;
import java.util.Iterator;

public class Scheduler {
    private static final String SCHD = "SCHD";
    private static Iterator<Server> iterator;

    public Scheduler(Iterator<Server> iterType) {
        /*Job scheduler should be able to be expanded later into multiple algo's as such it takes an iterator
        the iterator will provide the scheduler with a simple implementation of asking the iterator to pick the next server.
        */
        iterator = iterType;
    }

    public void scheduleJob(ConnectionManager conMan, ServerManager sManager, Job j) throws IOException { 
        //asks server to schedule the given job on the server decided by the iterator.
        conMan.serverMsg(String.format("%s %d %s\n", SCHD, j.getJobID(), iterator.next().getServerName()));
    }
}
