import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {
    private static final int PORT = 50000;
    // gets system username, HOST stores loopback address, rest are ds-server commands
    private static final String HELO = "HELO\n", QUIT = "QUIT\n", AUTH = String.format("AUTH %s\n", System.getProperty("user.name")), HOST = "127.0.0.1";
    BufferedReader dis;
    DataOutputStream dos;

    public Socket sock;

    public ConnectionManager() throws UnknownHostException, IOException {
        // open socket
        sock = new Socket(HOST, PORT);
        // store incoming chatters
        dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        // store messages to send
        dos = new DataOutputStream(sock.getOutputStream());
    }

    public ServerManager handShake(JobManager jManager, ConnectionManager cManager) throws IOException {
        // basic steps before handshake job
        serverMsg(HELO);
        serverMsg(AUTH);
        // jobManager makes more sense to handle the job
        return jManager.handshakeJob(cManager);
    }

    public String serverMsg(String msg) throws IOException {
        writeflush(dos, msg);
        // wait till message comes in
        while (!dis.ready())
            ;
        // return the line that came in
        return dis.readLine();
    }

    private static void writeflush(DataOutputStream dos, String s) throws IOException {
        // setup the command to send
        dos.write(s.getBytes());
        // pushhhhhh
        dos.flush();
    }

    public void quit() throws IOException {
        // exiting gracefully, so graceful.
        serverMsg(QUIT);
        sock.close();
    }

}
