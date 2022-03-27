import java.io.IOException;

public class client {
    public static void main(String[] args) throws IOException {
        //open socket to talk with server
        ConnectionManager cManager = new ConnectionManager();
        JobManager jManager = new JobManager();

        ServerManager sManager = cManager.handShake(jManager, cManager);

        while (jManager.jobAvaliable()) {
            jManager.getJob(cManager, sManager);
        }

        cManager.quit();
    }
}
