package ca.appolizer.outreach.model.request;

public class StudentUserRequest extends AbstractUserRequest {
    private int user_type_id;
    public int getUser_type_id() {
        return user_type_id;
    }

    public void setUser_type_id(int user_type_id) {
        this.user_type_id = user_type_id;
    }


}
