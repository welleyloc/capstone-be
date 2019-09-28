package welley.capstone.processors;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import welley.capstone.entities.Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class MultiItemProcessor {

    @Bean
    public CompositeItemProcessor<Product, Product> combineProcessors() throws Exception {
        List<ItemProcessor<Product, Product>> delegates = new ArrayList<>();
        delegates.add(new PriceProcessor());

        // add more processors here, if any

        CompositeItemProcessor<Product, Product> compositeItemProcessor = new CompositeItemProcessor<>();
        compositeItemProcessor.setDelegates(delegates);
        compositeItemProcessor.afterPropertiesSet();
        return compositeItemProcessor;
    }
}
