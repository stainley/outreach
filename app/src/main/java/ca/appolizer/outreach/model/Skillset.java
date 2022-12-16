package ca.appolizer.outreach.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class Skillset {

    private int id;
    @SerializedName("student_id")
    private int studentId;
    @SerializedName("skillset_id")
    private String skillsetId;
    @SerializedName("total_years_experience")
    private String totalYearsExperience;

    @SerializedName("skill")
    private Skill skill;

    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Skillset(String name) {
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

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    @Override
    public String toString() {
        return name;
    }
}
