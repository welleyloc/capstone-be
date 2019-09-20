package welley.capstone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {

    @Id
    private Integer id;
//    @Column(name = "productname")
    private String productName;
//    @Column(name = "supplier")
    private int supplier;
//    @Column(name = "category")
    private int category;
//    @Column(name = "availability")
    private int availability;
//    @Column(name = "fullprice")
    private double fullPrice;
//    @Column(name = "saleprice")
    private double salePrice;

    public Product(int id, String productName, int supplier, int category, int availability, double fullPrice, double salePrice) {
        this.id = id;
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.availability = availability;
        this.fullPrice = fullPrice;
        this.salePrice = salePrice;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getAvailability() {
        return availability;
    }

    public void setAvailability(int availability) {
        this.availability = availability;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public double getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    public double getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", category=" + category +
                ", availability=" + availability +
                ", fullPrice=" + fullPrice +
                ", salePrice=" + salePrice +
                '}';
    }
}
