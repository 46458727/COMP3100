import java.util.Comparator;

public class ServerSortBySize implements Comparator<Server> {

    // simple comparator for LRR server size comparison
    public int compare(Server a, Server b) {
        int comparison;
        comparison = a.getEstWaitTime().compareTo(b.getEstWaitTime());
        if (comparison == 0)    comparison = a.getCore().compareTo(b.getCore());
        
        return comparison;
    }

}
