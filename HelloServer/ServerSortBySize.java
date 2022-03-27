import java.util.Comparator;

public class ServerSortBySize implements Comparator<Server>{
    public int compare(Server a, Server b)
    {
        return b.getCore() - a.getCore();
    }

}
