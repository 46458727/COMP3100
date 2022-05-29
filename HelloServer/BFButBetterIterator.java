import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BFButBetterIterator implements Iterable<Server> {
    CommandExecution cExecution;
    List<Server> serverL;
    //List<Server> secondServerL;
    BFButBetterIterator(CommandExecution commandExecution) {
        cExecution = commandExecution;
        serverL = new ArrayList<>();
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
                
                serverL = cExecution.getsAvailable(JobManager.curJob);
            
                if (serverL.isEmpty())  {
                    serverL = cExecution.getsCapable(JobManager.curJob);
                }
                //serverL = cExecution.getsCapable(JobManager.curJob);
                Collections.sort(serverL, new ServerSortBySize());

                return serverL.get(0);
                   
            }
        };

        return iterator;
    }
    
}
