package ca.appolizer.outreach.data.dto;

import com.google.gson.annotations.SerializedName;

public class SkillsetDto {

    private int id;
    @SerializedName("student_id")
    private int studentId;
    @SerializedName("skillset_id")
    private String skillsetId;
    @SerializedName("total_years_experience")
    private String totalYearsExperience;

    @SerializedName("skill")
    private SkillDto skillDto;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SkillsetDto(String name) {
        this.name = name;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public String getSkillsetId() {
        return skillsetId;
    }

    public void setSkillsetId(String skillsetId) {
        this.skillsetId = skillsetId;
    }

    public String getTotalYearsExperience() {
        return totalYearsExperience;
    }

    public void setTotalYearsExperience(String totalYearsExperience) {
        this.totalYearsExperience = totalYearsExperience;
    }

    public SkillDto getSkill() {
        return skillDto;
    }

    public void setSkill(SkillDto skillDto) {
        this.skillDto = skillDto;
    }

    @Override
    public String toString() {
        return name;
    }
}
