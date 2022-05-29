public class Server {
    private String name, type, state;
    private Integer core, memory, disk, estWaitTime;

    public Server (String[] strArr) {
        //9 is the expected number of items for a Server construction
        if (strArr.length != 9) return;

        //[x] is what it's assigned to e.g. [0] is serverType
        type = strArr[0];
        //name is servernumber & type
        name = String.format("%s %s", strArr[0], strArr[1]);
        //isitrunning idle offline etc.
        state = strArr[2];
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

    public void setState(String state) { 
        this.state = state;
    }

    public String getServerName() {
        return name;
    }

    public String getServerType() {
        return type;
    }

    public void setEstWaitTime(String eString) {
        estWaitTime = Integer.parseInt(eString);
    }

    public Integer getEstWaitTime() {
        return estWaitTime;
    }
}
