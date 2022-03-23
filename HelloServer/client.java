import java.net.*;
import java.io.*;

public class client {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 50000;
    private static final String HELO = "HELO\n";
    //private static final String OK = "OK\n";
    private static final String AUTH = "AUTH USER\n";
    private static final String REDY = "REDY\n";
    private static final String QUIT = "QUIT\n";


    private static String serverMsg(DataOutputStream dos, String msg, BufferedReader dis) throws IOException {
        try {
            writeflush(dos, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dis.readLine();
    }

    public static void main(String[] args) throws IOException {
        try {
            Socket sock = new Socket(HOST, PORT);
            BufferedReader dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            //Helo & Auth
            System.out.println(serverMsg(dos, HELO, dis));
            System.out.println(serverMsg(dos, AUTH, dis));
            System.out.println(serverMsg(dos, REDY, dis));
            System.out.println(serverMsg(dos, QUIT, dis));


            
            
            sock.close();
        } catch (UnknownHostException e) {
            System.err.println("Unknown Host Exception: " + HOST);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IO Exception to: " + HOST);
            System.exit(1);
        }
    }

    private static void writeflush(DataOutputStream dos, String s) throws IOException {
        dos.write(s.getBytes());
        dos.flush();
    }
}