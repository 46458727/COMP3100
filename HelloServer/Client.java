import java.io.IOException;
import java.net.UnknownHostException;

public class Client {
    static ConnectionManager cManager;
    static CommandExecution cExecution;
    static JobManager jManager;

    private static void configure(String algo) throws UnknownHostException, IOException {
        cManager = new ConnectionManager();
        cExecution = new CommandExecution();
        jManager = new JobManager(cExecution, new Scheduler(algo, cExecution));
        cExecution.handShake(jManager);
        
        
        run();
    }

    private static void run() throws IOException{
        while (jManager.jobAvaliable()) {
            jManager.getJob();
        }
    }

    public static void main(String[] args) throws IOException {
        //if no arguments given new arg will be used || if args not in format -a algo
        if (args.length == 0 || args.length != 2 || !args[0].equals("-a")) { 
            String[] tArgs = {"-a", "BFC"};
            args = tArgs;
            System.out.println("No or invalid arguments were given, please enter in the form -a (LRR, BFC, FC), non-case sesnsitive, default (BFC) used");
        }
    
        configure(args[1]);

        cExecution.QUIT();
    }
}
