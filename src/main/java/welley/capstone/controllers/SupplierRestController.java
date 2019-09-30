package welley.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import welley.capstone.entities.Supplier;
import welley.capstone.service.TransactionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class SupplierRestController {

    @Autowired
    TransactionalService transactionalService;

//    @GetMapping("/getSuppliers")
//    public List<Supplier> getSupplierDashboard() {
//        return transactionalService.getAllSuppliers();
//    }
//
//    @PostMapping("/createSupplier")
//    public Supplier createCategory(Supplier supplier){
//        return transactionalService.saveSupplier(supplier);
//    }

}
