import java.util.Iterator;

public class FCIterator implements Iterable<Server> {
    CommandExecution cExecution;

    FCIterator (CommandExecution commandExecution) {
        cExecution = commandExecution;
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
                return cExecution.getsCapable(JobManager.curJob).get(0);
            }
        };

        

        return iterator;
    }
}

