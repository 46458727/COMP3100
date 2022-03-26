import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServerManager {
    public static List<Server> serverL;
    private static final String OK = "OK\n", GETS = "GETS All\n", RESF = "RESF";

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

    public void serverStatusUpdate(String[] sInfo) {
        
        for (Server s : serverL) {
            if (s.getServerName().equals(String.format("%s %s", sInfo[0], sInfo[1]))) { 
                if (sInfo[0].equals(RESF))  s.serverFailed();
                else s.serverRecovered();
                break;
            }
        }
    }

    public String serverPicker() {
        return serverL.stream().max(Comparator.comparingInt(Server::getCore)).get().getServerName();
    }
    
}
