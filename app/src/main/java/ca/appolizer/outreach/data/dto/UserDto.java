package ca.appolizer.outreach.data.dto;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class UserDto implements Serializable {
    private Long id;
    private String email;
    private String password;

    @SerializedName("student")
    private StudentDto studentDto;

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

    public StudentDto getStudent() {
        return studentDto;
    }

    public void setStudent(StudentDto studentDto) {
        this.studentDto = studentDto;
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
