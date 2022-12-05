package ca.appolizer.outreach.model;

public enum TypeUser {
    STUDENT(3), COMPANY(1);

    TypeUser(int code) {
        this.code = code;
    }

    private final int code;

    public int getCode() {
        return this.code;
    }
}