import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class CUMIterator implements Iterable<Server> {
    CommandExecution cExecution;
    List<Server> serverL;
    //List<Server> secondServerL;
    CUMIterator(CommandExecution commandExecution) {
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
                //if no servers avaliable gets capable
                if (serverL.isEmpty())  {
                    serverL = cExecution.getsCapable(JobManager.curJob);
                }
                //sort on wait time cpu size asc
                Collections.sort(serverL, new ServerSortBySize());
                //get best
                return serverL.get(0);
                   
            }
        };

        return iterator;
    }
    
}
