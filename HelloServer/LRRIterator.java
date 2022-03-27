import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class LRRIterator implements Iterable<Server>{
    private List<Server> lRRServers;

    public LRRIterator(List<Server> serverL) {
        
        lRRServers = serverL.stream()
            .filter(s -> s.getCore() == serverL.get(0).getCore())
            .collect(Collectors.toCollection(ArrayList::new));
            
    }




    @Override
    public Iterator<Server> iterator() {
        // lRR iterator
        Iterator<Server> iterator = new Iterator<Server>() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return true;
            }

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
