package ca.appolizer.outreach.model.response;

import java.io.Serializable;

import ca.appolizer.outreach.model.Student;

public class UserProfileResponse implements Serializable {
    private boolean status;
    private String completeSkills;
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
