import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServerManager {
    public static List<Server> serverL;
    private static final String OK = "OK\n", GETS = "GETS All\n";

    public ServerManager(ConnectionManager conMan) throws IOException {
        // Helo & Auth
        //To store a list of all servers
        serverL = new ArrayList<Server>();
        //gets a list of all servers to reference later
        conMan.serverMsg(GETS);
        for (conMan.serverMsg(OK); conMan.dis.ready(); serverL.add(new Server(conMan.dis.readLine().split(" "))));
        
        // close server list
        conMan.serverMsg(OK);
    }

    public String serverPicker() {
        return serverL.stream().max(Comparator.comparingInt(Server::getCore)).get().getServerName();
    }
    
}
