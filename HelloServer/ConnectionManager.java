import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class ConnectionManager {
    // gets system username, HOST stores loopback address, rest are ds-server
    // commands

    static BufferedReader dis;
    static DataOutputStream dos;

    public Socket sock;

    public ConnectionManager() throws UnknownHostException, IOException {
        String LOCALHOST = "127.0.0.1", HOST = LOCALHOST;
        int PORT = 50000;
        // open socket
        sock = new Socket(HOST, PORT);
        // store incoming chatters
        dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        // store messages to send
        dos = new DataOutputStream(sock.getOutputStream());
    }

    public static String serverMsg(String msg) throws IOException {
        say(msg);
        
        // return the line that came in
        return hear();
    }

    public static void say(String msg) throws IOException {
        writeflush(msg);

        // wait till message comes in
        while (!dis.ready());
    }

    public static String hear() throws IOException {
        return dis.ready() ? dis.readLine() : null;
    }

    private static void writeflush(String s) throws IOException {
        write(s);
        flush(dos);
    }

    public static void write(String s) throws IOException {
        dos.write(s.getBytes());
    }

    public static void flush(DataOutputStream dos) throws IOException {
        dos.flush();
    }
}
