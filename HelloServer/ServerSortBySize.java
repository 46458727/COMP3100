import java.util.Comparator;

public class ServerSortBySize implements Comparator<Server>{

    //simple comparator for LRR server size comparison
    public int compare(Server a, Server b)
    {
        return b.getCore() - a.getCore();
    }

}
