package model;

public class Dealer {

    private String id;
    private String name;
    private String address;
    private String phone;
    private Status status;

    public Dealer() {
    }

    public Dealer(String id, String name, String address, String phone, Status status) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return String.format("|%-4s|%-15s|%-22s|%-11s|%-8s|", this.id, this.name, this.address, this.phone, this.status);
    }

}
