package welley.capstone.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;
    private String supplierName;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<Product> productList;

    public Supplier(String supplierName) {
        this.supplierName = supplierName;
    }

    public Supplier() {}

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
}
