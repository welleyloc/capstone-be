package welley.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    @JoinColumn(name = "supplier_id")
    private int supplier;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("productList")
    private Category category;

    private boolean availability;
    private double fullPrice;
    private double salePrice;
    private double discountPercent;

    public Product() {
    }

    public Product(String productName, int supplier, Category category, boolean availability, double fullPrice, double salePrice, double discountPercent) {
        this.productName = productName;
        this.supplier = supplier;
        this.category = category;
        this.availability = availability;
        this.fullPrice = fullPrice;
        this.salePrice = salePrice;
        this.discountPercent = discountPercent;
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


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        System.out.println("setting category");
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

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

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
    public double getDiscountPercent() {
        BigDecimal formatter = new BigDecimal(this.discountPercent + "").setScale(2, RoundingMode.CEILING);
        return formatter.doubleValue();
    }


}
