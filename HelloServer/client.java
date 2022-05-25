import java.io.IOException;
import java.util.Arrays;

public class client {
    public static void main(String[] args) throws IOException {
        String[] testArgs = {"-a", "BFC"};
        //expandable args try catch with necessary arg -a and expandability by looking for even number of args assuming (-arg answ)
        if (! (Arrays.stream(testArgs).anyMatch("-a"::equals)) || testArgs.length > 1) {
            System.out.println("Please enter valid args (-a lrr)");
            System.exit(-1);
        }  

        // open socket to talk with server
        ConnectionManager cManager = new ConnectionManager();
        // creates a jobManager
        JobManager jManager = new JobManager();
        // creates the initial handshake
        ServerManager sManager = cManager.handShake(jManager, cManager, testArgs);
        // schedulse jobs till no more jobs
        while (jManager.jobAvaliable()) {
            jManager.getJob(cManager, sManager);
        }
        // exits "gracefully".
        cManager.quit();
    }
}
