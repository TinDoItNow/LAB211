package model;

public enum Status {
    Enabled("Enabled"), Disabled("Disabled");
    public final String status;

    Status(String status) {
        this.status = status;
    }
}
