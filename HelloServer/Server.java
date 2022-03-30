public class Server {
    private String serverName, serverType, state;
    private Integer core, memory, disk;

    public Server (String[] strArr) {
        if (strArr.length != 9) return;

        //store the servers type and name seperately
        serverType = strArr[0];
        serverName = String.format("%s %s", strArr[0], strArr[1]);
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
