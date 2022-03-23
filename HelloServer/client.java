import java.net.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Comparator;
import java.io.*;

public class client {

    

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 50000;
    private static final String HELO = "HELO\n";
    private static final String OK = "OK\n";
    private static final String AUTH = "AUTH USER\n";
    private static final String REDY = "REDY\n";
    private static final String GETS = "GETS All\n";
    private static final String SCHD = "SCHD";
    private static final String QUIT = "QUIT\n";
    
    public static List<Server> serverL = new ArrayList<Server>();
    public static List<Job> jobL = new ArrayList<Job>();

    private static String serverMsg(DataOutputStream dos, String msg, BufferedReader dis) throws IOException {
        try {
            writeflush(dos, msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (dis.ready())    return dis.readLine();
        return null;
    }

    private static String serverPicker(){
        return serverL.stream().max(Comparator.comparingInt(Server::getCORE)).get().serverName;
    }
    
    
    public static void main(String[] args) throws IOException {
        try {
            Socket sock = new Socket(HOST, PORT);
            BufferedReader dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

            //Helo & Auth
            System.out.println(serverMsg(dos, HELO, dis));
            System.out.println(serverMsg(dos, AUTH, dis));
            jobL.add(new Job(serverMsg(dos, REDY, dis).split(" ")));
            //get servers

            
            serverMsg(dos, GETS, dis);
            serverL.add(new Server(serverMsg(dos, OK, dis).split(" ")));
            
            do  {
                serverL.add(new Server(dis.readLine().split(" ")));
            } while (dis.ready());

            serverMsg(dos, OK, dis);
            
            //schd job loop
            while (dis.readLine() != QUIT) {
                for (Job j : jobL) {
                    serverMsg(dos, String.format("%s %d %s\n", SCHD, j.jobID, serverPicker()), dis);
                }
                
                jobL.clear();

                //jobL.add(new Job(serverMsg(dos, REDY, dis).split(" ")));
                if (dis.ready()) {
                    if (dis.readLine() == QUIT) break;
                }
            } 
                

                
            

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