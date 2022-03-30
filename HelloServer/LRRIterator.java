import java.util.Iterator;
import java.util.List;

public class LRRIterator implements Iterable<Server> {
    //servers to be used by iterator
    private List<Server> lRRServers;
    

    public LRRIterator(List<Server> serverL) {
        //removes all unimportant servers, serverL has been sorted descending.
        serverL.removeIf(server -> !server.getServerType().equals(serverL.get(0).getServerType()));
        lRRServers = serverL;
    }

    @Override
    public Iterator<Server> iterator() {
        // lRR iterator
        Iterator<Server> iterator = new Iterator<Server>() {
            private int currentIndex = 0;
            //there will always be a next as each time next is called it returns curr % max
            @Override
            public boolean hasNext() {
                return true;
            }

            //we want to loop over all items continously.
            @Override
            public Server next() {
                return lRRServers.get(currentIndex++ % lRRServers.size());
            }
        };
        return iterator;
    }
}
