package welley.capstone.entities;

import javax.persistence.*;

@Entity
public class Supplier {

    @Id
    @Column(name="supplier_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int supplierId;

    @Column(name="supplier_name")
    private String supplierName;

//    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL)
//    private List<Product> productList;

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

//    public List<Product> getProductList() {
//        return productList;
//    }
//
//    public void setProductList(List<Product> productList) {
//        this.productList = productList;
//    }
}
