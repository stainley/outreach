package ca.appolizer.outreach.data.network.responses;

import com.google.gson.annotations.SerializedName;

import ca.appolizer.outreach.data.model.Skillset;

public class StudentSkillsetResponse {

    private boolean status;
    private String message;
    @SerializedName("skillset")
    private Skillset skillset;

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

    public Skillset getSkillset() {
        return skillset;
    }

    public void setSkillset(Skillset skillset) {
        this.skillset = skillset;
    }
}
