package welley.capstone.batch;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import welley.capstone.dao.ProductRepository;
import welley.capstone.entities.Product;

import java.util.List;

@Component
public class Writer implements ItemWriter<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void write(List<? extends Product> products) throws Exception {
        System.out.println("Data saved: " + products);
        productRepository.saveAll(products);

    }
}

