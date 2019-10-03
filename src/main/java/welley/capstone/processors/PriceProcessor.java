package welley.capstone.processors;

import org.springframework.batch.item.ItemProcessor;
import welley.capstone.entities.Product;

// There is an error in the original products.csv where some sale prices are higher than the full price
// This processor is set to swap the prices as the database loads before conducting any further actions

public class PriceProcessor implements ItemProcessor<Product, Product> {
    public PriceProcessor() {
    }

    @Override
    public Product process(Product product) throws Exception {
        if (product.getSalePrice() > product.getFullPrice()) {
            double tempStorage = product.getSalePrice();
            product.setSalePrice(product.getFullPrice());
            product.setFullPrice(tempStorage);
        }
        return product;
    }
}
