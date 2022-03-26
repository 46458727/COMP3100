import java.net.*;
import java.util.ArrayList;
import java.util.List;

import java.util.Comparator;
import java.io.*;

public class client {

    private static final String HOST = "127.0.0.1";
    private static final int PORT = 50000;
    
    private static final String OK = "OK\n";
    
    private static final String REDY = "REDY\n";
  
    private static final String SCHD = "SCHD";
    private static final String QUIT = "QUIT\n";

    
    public static List<Job> jobL = new ArrayList<Job>();



    

    public static void main(String[] args) throws IOException {
        //open socket to talk with server
        Socket sock = new Socket(HOST, PORT);
        //create an data input & output stream to recieve and send commands
        BufferedReader dis = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        DataOutputStream dos = new DataOutputStream(sock.getOutputStream());

        ServerManager sManager = new ServerManager(dos, dis); //list of servers & info updater etc.

        jobL.add(new Job(serverMsg(dos, REDY, dis).split(" ")));
        // get servers
        

        System.out.println();
        // fill buffered reader with server message, guard buffered reader, at end of
        // loop add current readline as a server
        
        
        
        // schd job loop
        while (jobL.size() != 0) {
            for (Job j : jobL) {
                serverMsg(dos, String.format("%s %d %s\n", SCHD, j.jobID, serverPicker()), dis);
            }

            jobL.clear();
            String[] testJob = serverMsg(dos, REDY, dis).split(" ");
            if (testJob[0].equals("NONE"))
                break;
            jobL.add(new Job(testJob));
        }

        System.out.println(serverMsg(dos, QUIT, dis));
        sock.close();
    }
}
