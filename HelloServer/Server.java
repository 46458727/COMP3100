public class Server {
    String serverName, state;
    int curStartTime, core, memory, disk;

    public Server (String[] strArr) {
        if (strArr.length != 9) return;

        serverName = String.format("%s %s", strArr[0], strArr[1]);
        state = strArr[2];
        curStartTime = Integer.parseInt(strArr[3]);
        core = Integer.parseInt(strArr[4]);
        memory = Integer.parseInt(strArr[5]);
        disk = Integer.parseInt(strArr[6]);
    }

    public Integer getCORE() {
        return core;
    }
}
