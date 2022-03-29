import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LRRIterator implements Iterable<Server> {
    private List<Server> lRRServers;

    public LRRIterator(List<Server> serverL) {
        lRRServers = new ArrayList<>();
        
        Integer largestCore = serverL.get(0).getCore();
        String largestType = serverL.get(0).getServerType();

        for (Server s : serverL) {
            if (s.getCore().equals(largestCore) && s.getServerName().equals(largestType)) lRRServers.add(s);
            else break;
        };
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
