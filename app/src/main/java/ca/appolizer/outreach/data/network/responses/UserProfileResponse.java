package ca.appolizer.outreach.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ca.appolizer.outreach.data.model.Student;

public class UserProfileResponse implements Serializable {
    private boolean status;
    @SerializedName("completeSkills")
    private String completeSkills;

    @SerializedName("student")
    @Expose
    private Student student;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getCompleteSkills() {
        return completeSkills;
    }

    public void setCompleteSkills(String completeSkills) {
        this.completeSkills = completeSkills;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}
