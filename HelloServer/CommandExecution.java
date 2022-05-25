import java.io.IOException;
import java.util.ArrayList;

public class CommandExecution {
    public void handShake(JobManager jManager) throws IOException {
        // basic steps before handshake job
        HELO();
        AUTH();
        jManager.getJob();

    }

    private void HELO() throws IOException {
        ConnectionManager.serverMsg(Commands.HELO.get());
    }

    private void AUTH() throws IOException {
        ConnectionManager.serverMsg(Commands.AUTH.get());
    }

    public void QUIT() throws IOException {
        ConnectionManager.serverMsg(Commands.QUIT.get());
    }

    public String REDY() throws IOException {
        return ConnectionManager.serverMsg(Commands.REDY.get());
    }

    public void SCHD(int jobID, String serverName) throws IOException {
        ConnectionManager.serverMsg(String.format("%s %d %s\n", Commands.SCHD, jobID, serverName));
    }

    public ArrayList<Server> getsAll() throws IOException{
        ConnectionManager.serverMsg(String.format("%s%s %s %s %s\n", Commands.GETS.get(),Commands.ALL.get()));
        return getsGeneral();
    }

    public ArrayList<Server> getsAvailable(Job j) {
        try {
            ConnectionManager.serverMsg(String.format("%s%s %s %s %s\n", Commands.GETS.get(),Commands.AVALIABLE.get(), j.getCore(), j.getMemory(), j.getDisk()));
            return getsGeneral();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Server> getsCapable(Job j) {
        try {
            ConnectionManager.serverMsg(String.format("%s%s %s %s %s\n", Commands.GETS.get(),Commands.CAPABLE.get(), j.getCore(), j.getMemory(), j.getDisk()));
            return getsGeneral();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<Server> getsGeneral() throws IOException {
        ArrayList<Server> serverL = new ArrayList<Server>();
        for (serverL.add(new Server(ConnectionManager.serverMsg(Commands.OK.get()).split(" "))); ConnectionManager.dis.ready(); serverL.add(new Server(ConnectionManager.hear().split(" "))));
        ConnectionManager.serverMsg(Commands.OK.get());
        return serverL;
    }
}
