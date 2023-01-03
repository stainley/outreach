package ca.appolizer.outreach.data.network.responses;

import com.google.gson.annotations.SerializedName;

import ca.appolizer.outreach.data.dto.UserDto;

public class UserResponse {
    private int id;
    private String token;
    private String status;
    private String message;
    @SerializedName("user")
    private UserDto userDto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDto getUser() {
        return userDto;
    }

    public void setUser(UserDto userDto) {
        this.userDto = userDto;
    }
}
