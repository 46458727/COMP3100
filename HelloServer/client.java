import java.io.IOException;
import java.util.Arrays;

public class client {
    public static void main(String[] args) throws IOException {
        String[] testArgs = {"-a", "FC"};
        if (! (Arrays.stream(testArgs).anyMatch("-a"::equals))) return; 
        // open socket to talk with server
        ConnectionManager cManager = new ConnectionManager();
        // creates a jobManager
        JobManager jManager = new JobManager();
        // creates the initial handshake
        ServerManager sManager = cManager.handShake(jManager, cManager);
        // schedulse jobs till no more jobs
        while (jManager.jobAvaliable()) {
            jManager.getJob(cManager, sManager);
        }
        // exits "gracefully".
        cManager.quit();
    }
}
