import java.io.IOException;

public class client {
    private static final String QUIT = "QUIT\n";

    public static void main(String[] args) throws IOException {
        //open socket to talk with server
        ConnectionManager cManager = new ConnectionManager();
        JobManager jManager = new JobManager();

        cManager.handShake(jManager, cManager, null);

        ServerManager sManager = new ServerManager(cManager);
        Scheduler scheduler = new Scheduler();

        while (jManager.jobAvaliable()) {
            scheduler.scheduleJob(cManager, sManager, jManager.getJobList().get(jManager.getJobList().size() - 1));
            jManager.getJob(cManager, sManager);
        }

        System.out.println(cManager.serverMsg(QUIT));
        cManager.closeSocket();

    }
}
