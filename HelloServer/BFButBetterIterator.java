import java.io.IOException;
import java.util.Iterator;
import java.util.List;

public class BFButBetterIterator implements Iterable<Server> {
    private Server FAServer;
    CommandExecution cExecution;

    BFButBetterIterator(CommandExecution commandExecution) {
        cExecution = commandExecution;
    }

    @Override
    public Iterator<Server> iterator() {
        Iterator<Server> iterator = new Iterator<Server>() {
            //there will always be a next until REDY returns NONE.
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Server next() {
                List<Server> serverL;
                
                serverL = cExecution.getsAvailable(JobManager.curJob);
            
                if (serverL.isEmpty())  {
                    serverL = cExecution.getsCapable(JobManager.curJob);
                }

                return serverL.get(0);
                   
            }
        };

        return iterator;
    }
    
}
