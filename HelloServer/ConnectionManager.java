import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 50000;
    private static final String HELO = "HELO\n", AUTH = "AUTH USER\n";
    BufferedReader dis;
    DataOutputStream dos;

    public Socket sock;
    public ConnectionManager() throws UnknownHostException, IOException{
        sock = new Socket(HOST, PORT);
        dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        dos = new DataOutputStream(sock.getOutputStream());
    }

    public void handShake(JobManager jManager, ConnectionManager cManager, ServerManager sManager) throws IOException{
        serverMsg(HELO);
        serverMsg(AUTH);
        jManager.getJob(cManager, sManager);
    }
    public String serverMsg(String msg) throws IOException {
        writeflush(dos, msg);
        
        while (!dis.ready());

        return dis.readLine();
    }

    private static void writeflush(DataOutputStream dos, String s) throws IOException {
        dos.write(s.getBytes());
        dos.flush();
    }

    public void closeSocket() throws IOException{
        sock.close();
    }

}
