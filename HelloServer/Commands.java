public enum Commands {
    //All possible server commands
    HELO("HELO\n"),
    QUIT("QUIT\n"),
    OK("OK\n"),
    AUTH(String.format("AUTH%s\n", System.getProperty("user.name"))),
    REDY("REDY\n"),
    GETS("GETS "),
    CAPABLE("Capable"),
    AVALIABLE("Avail"),
    ALL("All"),
    SCHD("SCHD "),
    CNTJ("CNTJ "),
    EJWT("EJWT "),
    LSTJ("LSTJ "),
    PSHJ("PSHJ "),
    MIGJ("MIGJ "),
    KILJ("KILJ "),
    TERM("TERM ")
    ;
    
    private final String text;

    Commands(final String text) {
        this.text = text;
    }

    public String get() {
        return text;
    }
}
