package model;

public class Product {

    private String ProductID;
    private String ProductName;
    private float UnitPrice;
    private int Quantity;
    private String Status;

    public Product() {
        this.ProductID = null;
        this.ProductName = null;
        this.UnitPrice = 0.0f;
        this.Quantity = 0;
        this.Status = null;
    }

    public Product(String ProductID, String ProductName, float UnitPrice, int Quantity, String Status) {
        this.ProductID = ProductID;
        this.ProductName = ProductName;
        this.UnitPrice = UnitPrice;
        this.Quantity = Quantity;
        this.Status = Status;
    }

    public String getProductID() {
        return ProductID;
    }

    public void setProductID(String ProductID) {
        this.ProductID = ProductID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public float getUnitPrice() {
        return UnitPrice;
    }

    public void setUnitPrice(float UnitPrice) {
        this.UnitPrice = UnitPrice;
    }

    public void Print() {
        System.out.println("========== PRODUCT ==========\n");
        System.out.println("Product ID: " + getProductID());
        System.out.println("Product Name: " + getProductName());
        System.out.println("Unit Price: " + getUnitPrice());
        System.out.println("Quantity: " + getQuantity());
        System.out.println("Status: " + getStatus() + "\n");
    }
}
