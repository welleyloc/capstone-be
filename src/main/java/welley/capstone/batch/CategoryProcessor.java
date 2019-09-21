package welley.capstone.batch;

import org.springframework.batch.item.ItemProcessor;
import welley.capstone.entities.Product;

import java.util.HashMap;
import java.util.Map;

public class CategoryProcessor implements ItemProcessor<Product, Product> {

    private static final Map<String, String> CATEGORIES = new HashMap<>();

    public CategoryProcessor() {
        CATEGORIES.put("1", "Baby");
        CATEGORIES.put("2", "Beauty");
        CATEGORIES.put("3", "Food");
        CATEGORIES.put("4", "Garden");
        CATEGORIES.put("5", "Health");
        CATEGORIES.put("6", "Household");
        CATEGORIES.put("7", "International");
        CATEGORIES.put("8", "Pet");
        CATEGORIES.put("9", "Toys");
        CATEGORIES.put("10", "Wine & Beer");
    }

    @Override
    public Product process(Product product) throws Exception {
        String categoryId = product.getCategory();
        String category = CATEGORIES.get(categoryId);
        product.setCategory(category);
        return product;
    }
}
