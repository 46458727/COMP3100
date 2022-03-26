import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ServerManager {
    public static List<Server> serverL;
    private static final String HELO = "HELO\n";
    private static final String OK = "OK\n";
    private static final String AUTH = "AUTH USER\n";
    private static final String GETS = "GETS All\n";

    public ServerManager(DataOutputStream dos, BufferedReader dis) throws IOException {
        // Helo & Auth
        System.out.println(serverMsg(dos, HELO, dis));
        System.out.println(serverMsg(dos, AUTH, dis));
        //To store a list of all servers
        serverL = new ArrayList<Server>();
        //gets a list of all servers to reference later
        serverMsg(dos, GETS, dis);
        for (serverMsg(dos, OK, dis); dis.ready(); serverL.add(new Server(dis.readLine().split(" "))));
        
        // close server list
        serverMsg(dos, OK, dis);
    }

    public static String serverMsg(DataOutputStream dos, String msg, BufferedReader dis) throws IOException {
        writeflush(dos, msg);
        
        while (!dis.ready());
        return dis.readLine();

    }

    private static void writeflush(DataOutputStream dos, String s) throws IOException {
        dos.write(s.getBytes());
        dos.flush();
    }

    public static String serverPicker() {
        return serverL.stream().max(Comparator.comparingInt(Server::getCORE)).get().serverName;
    }
    
}
