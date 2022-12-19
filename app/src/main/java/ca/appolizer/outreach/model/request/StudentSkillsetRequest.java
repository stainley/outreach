package ca.appolizer.outreach.model.request;

import com.google.gson.annotations.SerializedName;

public class StudentSkillsetRequest implements UserRequest {

    @SerializedName("student_id")
    private int studentId;
    @SerializedName("skillset_id")
    private int skillsetId;
    @SerializedName("total_years_experience")
    private int totalYearsExperience;

    public StudentSkillsetRequest(int studentId, int skillsetId, int totalYearsExperience) {
        this.studentId = studentId;
        this.skillsetId = skillsetId;
        this.totalYearsExperience = totalYearsExperience;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getSkillsetId() {
        return skillsetId;
    }

    public void setSkillsetId(int skillsetId) {
        this.skillsetId = skillsetId;
    }

    public int getTotalYearsExperience() {
        return totalYearsExperience;
    }

    public void setTotalYearsExperience(int totalYearsExperience) {
        this.totalYearsExperience = totalYearsExperience;
    }
}
