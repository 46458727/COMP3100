import java.io.IOException;

public class Scheduler {
    private static final String SCHD = "SCHD";

    public void scheduleJob(ConnectionManager conMan, ServerManager sManager, Job j) throws IOException { 
        conMan.serverMsg(String.format("%s %d %s\n", SCHD, j.jobID, sManager.serverPicker()));
    }
}
