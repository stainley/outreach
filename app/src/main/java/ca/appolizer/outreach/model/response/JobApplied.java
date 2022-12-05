package ca.appolizer.outreach.model.response;

public class JobApplied {
    private long userId;
    private long jobId;

    public JobApplied() {
    }

    public JobApplied(long userId, long jobId) {
        this.userId = userId;
        this.jobId = jobId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }
}
