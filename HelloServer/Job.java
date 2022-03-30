public class Job {
    Integer submitTime, jobID, estRuntime, core, memory, disk;
    String status;
    public Job(String[] sArr) {
        //7 is expected number of items for a job to have
        if (sArr.length != 7) return;
        //[x] is what the value is assigned to e.g. [1] = submitTime for job  
        submitTime = Integer.parseInt(sArr[1]);
        jobID = Integer.parseInt(sArr[2]);
        estRuntime = Integer.parseInt(sArr[3]);
        core = Integer.parseInt(sArr[4]);
        memory = Integer.parseInt(sArr[5]);
        disk = Integer.parseInt(sArr[6]);
        status = "NEW";
    }
}
