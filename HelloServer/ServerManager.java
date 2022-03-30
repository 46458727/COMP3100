import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ServerManager {
    public static List<Server> serverL;
    private static final String OK = "OK\n", GETS = "GETS All\n", RESF = "RESF";
    public ServerManager(ConnectionManager conMan) throws IOException {
        //To store a list of all servers
        serverL = new ArrayList<Server>();
        //gets a list of all servers to reference later
        conMan.serverMsg(GETS);
        //Assumes message is DATA, goes ok, guards on more info left, adds server to list
        for (serverL.add(new Server(conMan.serverMsg(OK).split(" "))); conMan.dis.ready(); serverL.add(new Server(conMan.dis.readLine().split(" "))));

        //sort server list descending
        Collections.sort(serverL, new ServerSortBySize());

        // close server list
        conMan.serverMsg(OK);
    }

    public void serverStatusUpdate(String[] sInfo) {
        //Tracks if server is avaliable to send a job
        for (Server s : serverL) {
            if (s.getServerName().equals(String.format("%s %s", sInfo[0], sInfo[1]))) { 
                if (sInfo[0].equals(RESF))  s.serverFailed();
                else s.serverRecovered();
                break;
            }
        }
    }
}
