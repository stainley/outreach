package ca.appolizer.outreach.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JobListDto {
    @SerializedName("jobs")
    private List<JobDto> jobDtos;

    public List<JobDto> getJobs() {
        return jobDtos;
    }

    public void setJobs(List<JobDto> jobDtos) {
        this.jobDtos = jobDtos;
    }
}
