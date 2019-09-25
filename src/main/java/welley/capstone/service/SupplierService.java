package welley.capstone.service;

import welley.capstone.entities.Supplier;

import java.util.List;

public interface SupplierService {

    public Supplier saveSupplier(Supplier supplier);

    public List<Supplier> getAllSuppliers();


}
