package welley.capstone.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String productName;
    private int supplier;
    private int category;

    private boolean availability;
    private double fullPrice;
    private double salePrice;
    private double discountPercent;

    public Product(String productName, int supplier, int category, boolean availability, double fullPrice, double salePrice, double discountPercent) {
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.availability = availability;
        this.fullPrice = fullPrice;
        this.salePrice = salePrice;
        this.discountPercent = 0.00;
    }

    public Product() {}

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

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) { this.supplier = supplier; }

    @Transient
    public double getFullPrice() {
        BigDecimal formatter = new BigDecimal(this.fullPrice + "").setScale(2, RoundingMode.CEILING);
        return formatter.doubleValue();
    }

    public void setFullPrice(double fullPrice) {
        this.fullPrice = fullPrice;
    }

    @Transient
    public double getSalePrice() {
        BigDecimal formatter = new BigDecimal(this.salePrice + "").setScale(2, RoundingMode.CEILING);
        return formatter.doubleValue();
    }

    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    public void setDiscountPercent() {
        this.discountPercent = ((getFullPrice() - getSalePrice()) / getFullPrice() * 100);
    }

    @Transient
    public double getDiscountPercent()
    {
        BigDecimal formatter = new BigDecimal(this.discountPercent + "").setScale(2, RoundingMode.CEILING);
        return formatter.doubleValue();
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", supplier='" + supplier + '\'' +
                ", category='" + category + '\'' +
                ", availability='" + availability + '\'' +
                ", fullPrice=" + fullPrice +
                ", salePrice=" + salePrice +
                ", discountPercent=" + discountPercent +
                '}';
    }
}
