package ca.appolizer.outreach.data.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class JobEntity {
    @PrimaryKey
    private long id;

    private int userId;
    private String name, description;

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
}
