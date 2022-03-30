public class Job {
    Integer submitTime, jobID, estRuntime, core, memory, disk;
    String status;
    public Job(String[] sArr) {
        if (sArr.length != 7) return;
        //is a comment really necessary to explain this?
        submitTime = Integer.parseInt(sArr[1]);
        jobID = Integer.parseInt(sArr[2]);
        estRuntime = Integer.parseInt(sArr[3]);
        core = Integer.parseInt(sArr[4]);
        memory = Integer.parseInt(sArr[5]);
        disk = Integer.parseInt(sArr[6]);
        status = "NEW";
    }
}
