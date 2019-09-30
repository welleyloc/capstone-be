package welley.capstone.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name="PRODUCT")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String productName;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SUPPLIER_ID")
    @JsonIgnoreProperties("supplierList")
    private Supplier supplier;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    @JsonIgnoreProperties("productList")
    private Category category;

    private boolean availability;
    private double fullPrice;
    private double salePrice;
    private double discountPercent;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) { this.supplier = supplier; }

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

}
