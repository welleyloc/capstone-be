package welley.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;
import welley.capstone.service.TransactionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierRestController {

    @Autowired
    TransactionalService transactionalService;

    @GetMapping("/getSupplier/{supplierId}")
    public Supplier getSupplier(@PathVariable int supplierId) { return transactionalService.getSupplierById(supplierId);}

    @GetMapping("/getSuppliers")
    public List<Supplier> getSupplierList() {
        return transactionalService.getAllSuppliers();
    }

    @GetMapping("/getProductsInSup/{supplierId}")
    public List<Product> getProductsBySupplier(@PathVariable int supplierId) { return transactionalService.getProductsInSupplier(supplierId);}

    @PostMapping("/createSupplier")
    public Supplier createSupplier(@RequestBody Supplier supplier) {
        return transactionalService.createSupplier(supplier);
    }

    @PutMapping("/updateSupplier/{supplierId}")
    public Supplier updateSupplier(String supplierName, @PathVariable int supplierId) {
        return transactionalService.updateSupplier(supplierName, supplierId);
    }

    @DeleteMapping("/supplier/{supplierId}")
    public void deleteSupplier(@PathVariable int supplierId) {
        Supplier supplier = transactionalService.getSupplierById(supplierId);
        transactionalService.deleteSupplier(supplier);
    }

}
