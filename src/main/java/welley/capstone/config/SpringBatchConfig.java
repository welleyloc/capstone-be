package welley.capstone.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import welley.capstone.entities.Product;

@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Configuration
@EnableBatchProcessing //need this annotation for JobLauncher bean definition
public class SpringBatchConfig {

    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                   ItemReader<Product> productItemReader, CompositeItemProcessor compositeItemProcessor,
                   ItemWriter<Product> itemWriter) {

        Step step = stepBuilderFactory.get("Products-File-Load")
                .<Product, Product>chunk(100)
                .reader(productItemReader)
                .processor(compositeItemProcessor)
                .writer(itemWriter)
                .build();

        return jobBuilderFactory.get("Products-Load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<Product> productItemReader(
            @Value("${input1}") Resource resource) {

        FlatFileItemReader<Product> productFileItemReader = new FlatFileItemReader<>();
        productFileItemReader.setResource(resource);
        productFileItemReader.setName("Product-File-Reader");
        productFileItemReader.setLinesToSkip(1); // skips the header
        productFileItemReader.setLineMapper(new DefaultLineMapper<Product>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"id", "productName", "category", "fullPrice",
                        "salePrice", "availability", "supplier"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {{
                setTargetType(Product.class);
            }});
        }});
        return productFileItemReader;
    }

}

