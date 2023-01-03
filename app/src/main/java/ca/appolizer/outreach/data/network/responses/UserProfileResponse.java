package ca.appolizer.outreach.data.network.responses;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import ca.appolizer.outreach.data.dto.StudentDto;

public class UserProfileResponse implements Serializable {
    private boolean status;
    @SerializedName("completeSkills")
    private String completeSkills;

    @SerializedName("student")
    @Expose
    private StudentDto studentDto;

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

    public StudentDto getStudent() {
        return studentDto;
    }

    public void setStudent(StudentDto studentDto) {
        this.studentDto = studentDto;
    }
}
