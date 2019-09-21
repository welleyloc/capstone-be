package welley.capstone.processors;

import org.springframework.batch.item.ItemProcessor;
import welley.capstone.entities.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

// discount % = (fullPrice - salePrice) / fullPrice * 100

public class DiscountProcessor implements ItemProcessor<Product, Product> {

    public DiscountProcessor() {
    }

    @Override
    public Product process(Product product) throws Exception {
        double calculatedDiscount = ((product.getFullPrice() - product.getSalePrice()) / product.getFullPrice() * 100);
        BigDecimal formatter = new BigDecimal(calculatedDiscount).setScale(2, RoundingMode.CEILING);

        product.setDiscountPercent(formatter.doubleValue());
        return product;
    }
}
