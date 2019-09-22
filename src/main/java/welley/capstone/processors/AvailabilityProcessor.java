package welley.capstone.processors;

import org.springframework.batch.item.ItemProcessor;
import welley.capstone.entities.Product;

public class AvailabilityProcessor implements ItemProcessor<Product, Product> {
    public AvailabilityProcessor() {
    }

    @Override
    public Product process(Product product) throws Exception {
        if (product.getAvailability().equals("1")) {
            product.setAvailability("in stock");
        }

        else if (product.getAvailability().equals("0")) {
            product.setAvailability("out of stock");
        }
        return product;
    }
}
