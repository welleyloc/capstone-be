package welley.capstone.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import welley.capstone.entities.Supplier;
import welley.capstone.dal.SupplierRepository;

import java.util.List;

@Component
public class SupplierWriter implements ItemWriter<Supplier> {


    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public void write(List<? extends Supplier> suppliers) throws Exception {
        System.out.println("Suppliers saved: " + suppliers);
        supplierRepository.saveAll(suppliers);
    }
}
