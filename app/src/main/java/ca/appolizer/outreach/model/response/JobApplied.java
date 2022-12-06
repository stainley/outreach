package ca.appolizer.outreach.model.response;

public class JobApplied {
    private long userId;
    private long jobId;
    private String email;

    public JobApplied() {
    }

    public JobApplied(long userId, long jobId, String email) {
        this.userId = userId;
        this.jobId = jobId;
        this.email = email;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
