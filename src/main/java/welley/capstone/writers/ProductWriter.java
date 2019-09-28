package welley.capstone.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import welley.capstone.dal.ProductRepository;
import welley.capstone.entities.Product;

import java.util.List;

@Component
public class ProductWriter implements ItemWriter<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        System.out.println("Products saved: " + products);
        productRepository.saveAll(products);
    }
}

