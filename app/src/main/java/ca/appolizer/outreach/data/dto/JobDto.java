package ca.appolizer.outreach.data.dto;


import com.google.gson.annotations.SerializedName;

public class JobDto {
    private long id;
    @SerializedName("user_id")
    private int userId;
    private String name;
    private String description;

    public JobDto() {
    }

    public JobDto(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
