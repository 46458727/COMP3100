import java.io.IOException;

public class CommandExecution {

    ConnectionManager cManager;

    CommandExecution(ConnectionManager cMan) {
        cManager = cMan;
    }

    private static enum Commands {
        HELO("HELO\n"),
        QUIT("QUIT\n"),
        AUTH(String.format("AUTH%s\n", System.getProperty("user.name"))),
        REDY("REDY\n"),
        GETS("GETS "),
        CNTJ("CNTJ"),
        EJWT("EJWT"),
        LSTJ("LSTJ"),
        PSHJ("PSHJ"),
        MIGJ("MIGJ"),
        KILJ("KILJ"),
        TERM("TERM")
        ;
        
        private final String text;

        Commands(final String text) {
            this.text = text;
        }

        public String get() {
            return text;
        }
    }

    public ServerManager handShake(JobManager jManager, ConnectionManager cManager, String[] args) throws IOException {
        // basic steps before handshake job
        HELO();
        AUTH();
        // jobManager makes more sense to handle the job
        return jManager.handshakeJob(cManager, args);
    }

    private void HELO() throws IOException {
        ConnectionManager.serverMsg(Commands.HELO.get());
    }

    private void AUTH() throws IOException {
        ConnectionManager.serverMsg(Commands.AUTH.get());
    }

}
