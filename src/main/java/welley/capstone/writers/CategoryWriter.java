package welley.capstone.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import welley.capstone.dal.ProductRepository;
import welley.capstone.entities.Category;
import welley.capstone.dal.CategoryRepository;
import welley.capstone.entities.Product;

import java.util.List;

@Component
public class CategoryWriter implements ItemWriter<Category> {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public void write(List<? extends Category> categories) throws Exception {

        System.out.println("Categories saved: " + categories);
        categoryRepository.saveAll(categories);

    }
}
