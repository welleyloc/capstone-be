package welley.capstone.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import welley.capstone.dal.SupplierRepository;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;


@Configuration
@EnableBatchProcessing //need this annotation for JobLauncher bean definition
@EnableTransactionManagement
public class SpringBatchConfig implements BatchConfigurer {

    // source for postgres writers
    @Resource(name = "dataSource")
    private DataSource dataSource;

    @Bean
    public Job job(StepBuilderFactory stepBuilderFactory,
                   JobBuilderFactory jobBuilderFactory,

                   FlatFileItemReader<Product> productItemReader,
                   FlatFileItemReader<Category> categoryItemReader,
                   FlatFileItemReader<Supplier> supplierItemReader,

                   CompositeItemProcessor compositeItemProcessor,

//                   ItemWriter<Product> productItemWriter,
//                   ItemWriter<Category> categoryItemWriter,
//                   ItemWriter<Supplier> supplierItemWriter

                   JdbcBatchItemWriter<Product> jdbcProductBatchItemWriter,
                   JdbcBatchItemWriter<Category> jdbcCategoryBatchItemWriter,
                   JdbcBatchItemWriter<Supplier> jdbcSupplierBatchItemWriter

    ) {

        Step step1 = stepBuilderFactory.get("Products-File-Load")
                .<Product, Product>chunk(50)
                .reader(productItemReader)
                .processor(compositeItemProcessor)
                .writer(jdbcProductBatchItemWriter)
                .build();
        System.out.println("PRODUCTS STEP1 COMPILED");

        Step step2 = stepBuilderFactory.get("Category-File-Load")
                .<Category, Category>chunk(50)
                .reader(categoryItemReader)
                .writer(jdbcCategoryBatchItemWriter)
                .build();
        System.out.println("CATEGORIES STEP2 COMPILED");

        Step step3 = stepBuilderFactory.get("Supplier-File-Load")
                .<Supplier, Supplier>chunk(50)
                .reader(supplierItemReader)
                .writer(jdbcSupplierBatchItemWriter)
                .build();
        System.out.println("SUPPLIERS STEP3 COMPILED");

        return jobBuilderFactory.get("CSV-Reader-Job")
                .incrementer(new RunIdIncrementer())
                .start(step3)
                .next(step2)
                .next(step1)
                .build();
    }

    @Bean
    public FlatFileItemReader<Product> productItemReader(@Value("${input1}") org.springframework.core.io.Resource resource) {
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
    public FlatFileItemReader<Category> categoryItemReader(@Value("${input2}") org.springframework.core.io.Resource resource) {
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
    public FlatFileItemReader<Supplier> supplierItemReader(@Value("${input3}") org.springframework.core.io.Resource resource) {
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


    // Postgres database configurations

    @Override
    public JobRepository getJobRepository() throws Exception {
        JobRepositoryFactoryBean factory = new JobRepositoryFactoryBean();
        factory.setDatabaseType("POSTGRES");
        factory.setDataSource(this.dataSource);
        factory.setTransactionManager(getTransactionManager());
        factory.afterPropertiesSet();
        return factory.getObject();
    }

    @Override
    public PlatformTransactionManager getTransactionManager() throws Exception {
        return new DataSourceTransactionManager(this.dataSource);
    }

    @Override
    public JobLauncher getJobLauncher() throws Exception {
        return null;
    }

    @Override
    public JobExplorer getJobExplorer() throws Exception {
        return null;
    }

    @Bean
    public JdbcBatchItemWriter<Product> jdbcProductBatchItemWriter() {
        JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(this.dataSource);
        writer.setSql("INSERT INTO product (id, product_name, category_id, supplier_id, full_price, sale_price, discount_percent) VALUES (:id, :productName, :category, :supplier, :fullPrice, :salePrice, :discountPercent)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        System.out.println("JDBC PRODUCT WRITER COMPILED");

        return writer;
    }

    @Bean
    public JdbcBatchItemWriter<Category> jdbcCategoryBatchItemWriter() {
        JdbcBatchItemWriter<Category> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(this.dataSource);
        writer.setSql("INSERT INTO category (category_id, categoryName) VALUES (:categoryId, :categoryName)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        System.out.println("JDBC CATEGORY WRITER COMPILED");

        return writer;
    }

    @Autowired
    SupplierRepository supplierRepository;

    @Bean
    public JdbcBatchItemWriter<Supplier> jdbcSupplierBatchItemWriter() {
        JdbcBatchItemWriter<Supplier> writer = new JdbcBatchItemWriter<>();
        writer.setDataSource(this.dataSource);
        writer.setSql("INSERT INTO supplier (supplier_id, supplierName) VALUES (:supplierId, :supplierName)");
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        System.out.println("JDBC SUPPLIER WRITER COMPILED");

        List<Supplier> suppliers = this.supplierRepository.findAll();

        System.out.println(suppliers);

        return writer;
    }

}