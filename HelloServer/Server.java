public class Server {
    private String serverName, serverType, state;
    private Integer core, memory, disk;
    //private int curStartTime;

    public Server (String[] strArr) {
        if (strArr.length != 9) return;
        serverType = strArr[0];
        //serverID = Integer.parseInt(strArr[1]);
        serverName = String.format("%s %s", strArr[0], strArr[1]);
        state = strArr[2];
        //curStartTime = Integer.parseInt(strArr[3]);
        core = Integer.parseInt(strArr[4]);
        memory = Integer.parseInt(strArr[5]);
        disk = Integer.parseInt(strArr[6]);
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
