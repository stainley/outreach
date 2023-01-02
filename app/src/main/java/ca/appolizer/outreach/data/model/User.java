package ca.appolizer.outreach.data.model;

import java.io.Serializable;

public class User implements Serializable {
    private Long id;
    private String email;
    private String password;
    private Student student;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public enum TypeUser {
        STUDENT(3), EMPLOYEE(2), ADMIN(1);

        TypeUser(int code) {
            this.code = code;
        }

        private final int code;

        public int getCode() {
            return this.code;
        }
    }
}
