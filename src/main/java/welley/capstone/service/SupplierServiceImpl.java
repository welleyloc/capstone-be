package welley.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import welley.capstone.entities.Supplier;
import welley.capstone.repos.SupplierRepository;

import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }
}
