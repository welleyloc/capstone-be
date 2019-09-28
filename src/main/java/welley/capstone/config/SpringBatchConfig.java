package welley.capstone.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;


@Configuration
@EnableBatchProcessing //need this annotation for JobLauncher bean definition
public class SpringBatchConfig {

    @Bean
    public Job job(StepBuilderFactory stepBuilderFactory,
                   JobBuilderFactory jobBuilderFactory,

                   FlatFileItemReader<Product> productItemReader,
                   FlatFileItemReader<Category> categoryItemReader,
                   FlatFileItemReader<Supplier> supplierItemReader,

                   CompositeItemProcessor compositeItemProcessor,

                   ItemWriter<Product> productItemWriter,
                   ItemWriter<Category> categoryItemWriter,
                   ItemWriter<Supplier> supplierItemWriter
    ) {

        Step step1 = stepBuilderFactory.get("Products-File-Load")
                .<Product, Product>chunk(50)
                .reader(productItemReader)
                .processor(compositeItemProcessor)
                .writer(productItemWriter)
                .build();
        System.out.println("PRODUCTS STEP1 COMPILED");

        Step step2 = stepBuilderFactory.get("Category-File-Load")
                .<Category, Category>chunk(50)
                .reader(categoryItemReader)
                .writer(categoryItemWriter)
                .build();
        System.out.println("CATEGORIES STEP2 COMPILED");

        Step step3 = stepBuilderFactory.get("Supplier-File-Load")
                .<Supplier, Supplier>chunk(50)
                .reader(supplierItemReader)
                .writer(supplierItemWriter)
                .build();
        System.out.println("SUPPLIERS STEP3 COMPILED");

        return jobBuilderFactory.get("CSV-Reader-Job")
                .incrementer(new RunIdIncrementer())
                .start(step1)
                .next(step2)
                .next(step3)
                .build();
    }

    @Bean
    public FlatFileItemReader<Product> productItemReader(@Value("${input1}") Resource resource) {
        FlatFileItemReader<Product> productFileItemReader = new FlatFileItemReader<>();
        productFileItemReader.setResource(resource);
        productFileItemReader.setName("Product-File-Reader");
        productFileItemReader.setLinesToSkip(1); // skips the header
        productFileItemReader.setLineMapper(new DefaultLineMapper<Product>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("id", "productName", "category", "fullPrice",
                        "salePrice", "availability", "supplier");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Product>() {{
                setTargetType(Product.class);
            }});
        }});
        System.out.println("PRODUCTS CSV FILE WAS READ!");
        return productFileItemReader;
    }

    @Bean
    public FlatFileItemReader<Category> categoryItemReader(@Value("${input2}") Resource resource) {
        FlatFileItemReader<Category> categoryFileItemReader = new FlatFileItemReader<>();
        categoryFileItemReader.setResource(resource);
        categoryFileItemReader.setName("Category-File-Reader");
        categoryFileItemReader.setLinesToSkip(1);

        DefaultLineMapper defaultLineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        BeanWrapperFieldSetMapper beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper();

        delimitedLineTokenizer.setNames("categoryId", "categoryName");
        defaultLineMapper.setLineTokenizer(delimitedLineTokenizer);
        defaultLineMapper.setFieldSetMapper(beanWrapperFieldSetMapper);

        categoryFileItemReader.setLineMapper(defaultLineMapper);
        beanWrapperFieldSetMapper.setTargetType(Category.class);

        System.out.println("CATEGORIES CSV FILE WAS READ!");
        return categoryFileItemReader;
    }

    @Bean
    public FlatFileItemReader<Supplier> supplierItemReader(@Value("${input3}") Resource resource) {
        FlatFileItemReader<Supplier> supplierFileItemReader = new FlatFileItemReader<>();
        supplierFileItemReader.setResource(resource);
        supplierFileItemReader.setName("Supplier-File-Reader");
        supplierFileItemReader.setLinesToSkip(1);
        supplierFileItemReader.setLineMapper(new DefaultLineMapper<Supplier>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("supplierId", "supplierName");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Supplier>() {{
                setTargetType(Supplier.class);
            }});
        }});

        System.out.println("SUPPLIERS CSV FILE WAS READ!");
        return supplierFileItemReader;

    }

}