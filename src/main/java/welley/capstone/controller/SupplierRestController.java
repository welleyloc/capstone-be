package welley.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import welley.capstone.entities.Supplier;
import welley.capstone.service.SupplierService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierRestController {

    @Autowired
    SupplierService supplierService;

    @GetMapping("/getSuppliers")
    public List<Supplier> getSupplierDashboard() {
        return supplierService.getAllSuppliers();
    }

    @PostMapping("/createSupplier")
    public Supplier createCategory(Supplier supplier){
        return supplierService.saveSupplier(supplier);
    }

}
