package welley.capstone.processors;

import org.springframework.batch.item.ItemProcessor;
import welley.capstone.entities.Product;

// There is an error in products.csv where some sale prices are higher than the full price
// This processor is set to swap the prices

public class PriceProcessor implements ItemProcessor<Product, Product> {
    public PriceProcessor() {
    }

    @Override
    public Product process(Product product) throws Exception {
        if(product.getSalePrice() > product.getFullPrice()) {
            double temp = product.getSalePrice();
            product.setSalePrice(product.getFullPrice());
            product.setFullPrice(temp);
        }
        return product;
    }
}
