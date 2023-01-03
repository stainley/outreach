package ca.appolizer.outreach.data.network.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import ca.appolizer.outreach.data.dto.SkillsetDto;

public class SkillsetResponse {

    @SerializedName("skillset")
    private List<SkillsetDto> skillsetDto;

    public List<SkillsetDto> getSkillset() {
        return skillsetDto;
    }

    public void setSkillset(List<SkillsetDto> skillsetDto) {
        this.skillsetDto = skillsetDto;
    }
}
