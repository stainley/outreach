package ca.appolizer.outreach.data.network.responses;

import com.google.gson.annotations.SerializedName;

import ca.appolizer.outreach.data.dto.SkillsetDto;

public class StudentSkillsetResponse {

    private boolean status;
    private String message;
    @SerializedName("skillsetDto")
    private SkillsetDto skillsetDto;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public SkillsetDto getSkillset() {
        return skillsetDto;
    }

    public void setSkillset(SkillsetDto skillsetDto) {
        this.skillsetDto = skillsetDto;
    }
}
