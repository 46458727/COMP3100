public class Job {
    int submitTime, jobID, estRuntime, core, memory, disk;

    public Job(String[] sArr) {
        if (sArr.length != 6) return;

        submitTime = Integer.parseInt(sArr[0]);
        jobID = Integer.parseInt(sArr[1]);
        estRuntime = Integer.parseInt(sArr[2]);
        core = Integer.parseInt(sArr[3]);
        memory = Integer.parseInt(sArr[4]);
        disk = Integer.parseInt(sArr[5]);
    }
}
