import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LRRIterator implements Iterable<Server> {
    //servers to be used by iterator
    private List<Server> lRRServers;

    public LRRIterator(List<Server> serverL) {
        //serverL has been pre-sorted by the Servermanager
        //to be used for LRR 
        String largestType = serverL.get(0).getServerType();
        //acceses each server, tests if server type matches the largest type, if it does add it to the collection, if it doesn ignore.
        lRRServers = serverL.stream().filter(i -> i.getServerType().equals(largestType)).collect(Collectors.toList());

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

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
        return iterator;
    }
}
