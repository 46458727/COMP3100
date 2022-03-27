import java.io.IOException;
import java.util.Iterator;

public class Scheduler {
    private static final String SCHD = "SCHD";
    private static Iterator<Server> iterator;

    public Scheduler(Iterator<Server> iterType) {
        iterator = iterType;
    }

    public void scheduleJob(ConnectionManager conMan, ServerManager sManager, Job j) throws IOException { 
        conMan.serverMsg(String.format("%s %d %s\n", SCHD, j.jobID, iterator.next().getServerName()));
    }
}
