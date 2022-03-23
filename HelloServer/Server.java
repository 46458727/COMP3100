public class Server {
    String serverName, serverType,state;
    int curStartTime, core, memory, disk, serverID;
    
    /*public Server(String[] args) throws NumberFormatException{
        this(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
    }

    public Server(String _serverType, String _serverID, String _state, String _curStartTime, String _core, String _memory, String _disk) {
        this(_serverType, Integer.parseInt(_serverID), _state, Integer.parseInt(_curStartTime), Integer.parseInt(_core), Integer.parseInt(_memory), Integer.parseInt(_disk));
    }

    public Server(String _serverType, int _serverID, String _state, int _curStartTime, int _core, int _memory, int _disk) {
        serverType = _serverType;
        serverID = _serverID;
        state = _state;
        curStartTime = _curStartTime;
        core = _core;
        memory = _memory;
        disk = _disk;

        serverName = String.format("%s %d", serverType, serverID);
    }*/

    public Server (String[] strArr) throws ArrayIndexOutOfBoundsException {
        if (strArr.length != 7) return;

        serverName = String.format("%s %s", strArr[0], strArr[1]);
        state = strArr[2];
        curStartTime = Integer.parseInt(strArr[3]);
        core = Integer.parseInt(strArr[4]);
        memory = Integer.parseInt(strArr[5]);
        disk = Integer.parseInt(strArr[6]);

    }
    
}
