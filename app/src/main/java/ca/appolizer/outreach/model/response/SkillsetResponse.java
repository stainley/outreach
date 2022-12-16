package ca.appolizer.outreach.model.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ca.appolizer.outreach.model.Skillset;

public class SkillsetResponse {

    @SerializedName("skillset")
    private List<Skillset> skillset;

    public List<Skillset> getSkillset() {
        return skillset;
    }

    public void setSkillset(List<Skillset> skillset) {
        this.skillset = skillset;
    }
}
