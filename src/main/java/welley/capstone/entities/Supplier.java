package welley.capstone.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Supplier {

    @Id
    @Column(name = "supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @OneToMany(mappedBy = "supplier", cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    private List<Product> productList;

    public Supplier() {
    }

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public void addProductToList(Product product) {
        productList.add(product);
        product.setSupplier(this);
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", productList=" + productList +
                '}';
    }
}
