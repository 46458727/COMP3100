public class Server {
    private String serverName, serverType, state;
    private Integer core, memory, disk;

    public Server (String[] strArr) {
        //9 is the expected number of items for a Server construction
        if (strArr.length != 9) return;

        //[x] is what it's assigned to e.g. [0] is serverType
        serverType = strArr[0];
        //name is servernumber & type
        serverName = String.format("%s %s", strArr[0], strArr[1]);
        //isitrunning idle offline etc.
        state = strArr[2];
        core = Integer.parseInt(strArr[4]);
        memory = Integer.parseInt(strArr[5]);
        disk = Integer.parseInt(strArr[6]);

        //not all of the get() functions are currently used but will be implemented in stage 2 and as such are useful.
    }

    public Integer getCore() {
        return core;
    }

    public Integer getMemory() {
        return memory;
    }

    public Integer getDisk() {
        return disk;
    }

    public String getState() {
        return state;
    }

    public void serverFailed() {
        this.state = "FAILED";
    }

    public void serverRecovered() {
        this.state = "BOOTING";
    }

    public String getServerName() {
        return serverName;
    }

    public String getServerType() {
        return serverType;
    }
}
