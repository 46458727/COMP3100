import java.io.IOException;
import java.util.Iterator;

public class FCIterator implements Iterable<Server> {
    ConnectionManager cManager;
    private Server FCServer;

    private static final String GETSC = "GETS Capable", OK = "OK\n";
    
    FCIterator(ConnectionManager cManager, JobManager jManager) {
        this.cManager = cManager;
    } 

    @Override
    public Iterator<Server> iterator() {
        
        // lRR iterator
        Iterator<Server> iterator = new Iterator<Server>() {
            
            //there will always be a next as each time next is called it returns curr % max
            @Override
            public boolean hasNext() {
                return true;
            }

            //we want to loop over all items continously.
            @Override
            public Server next() {
                try {
                    
                    cManager.serverMsg(String.format("%s %s %s %s\n", GETSC, JobManager.curJob.getCore(), JobManager.curJob.getMemory(), JobManager.curJob.getDisk()));
                    
                    for (FCServer = new Server(cManager.serverMsg(OK).split(" ")); cManager.dis.ready(); cManager.dis.readLine());

                    cManager.serverMsg(OK);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return FCServer;
            }
        };

        

        return iterator;
    }
}
