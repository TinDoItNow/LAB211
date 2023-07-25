package model;

public class User {

    private String username;
    private String password;
    private String role;
    private Status status;

    public User() {
    }

    public User(String username, String password, String role, Status status) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString() {
        return String.format("|%s|%s|%s|%s|", this.username, this.password, this.role, this.status);
    }
}
