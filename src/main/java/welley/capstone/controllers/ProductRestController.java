package welley.capstone.controllers;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import welley.capstone.entities.Product;
import welley.capstone.service.TransactionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    TransactionalService transactionalService;

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return transactionalService.getProductById(id);
    }

    @GetMapping("/dashboard")
    public List<Product> getProductList() {
        return transactionalService.getAllProducts();
    }

    @DeleteMapping("/product/{id}")
    public void deleteProd(@PathVariable int id) {
        Product product = transactionalService.getProductById(id);
        transactionalService.deleteProduct(product);
    }

    @PostMapping("/createProduct/{categoryId}/{supplierId}")
    public Product createProd(@RequestBody Product product, @PathVariable int categoryId, @PathVariable int supplierId) {
        return transactionalService.createProduct(product, categoryId, supplierId);
    }

    @PutMapping("/updateProduct/{id}/{categoryId}/{supplierId}")
    public Product updateProduct(@RequestBody Product product, @PathVariable int id, @PathVariable int categoryId, @PathVariable int supplierId) {
        return transactionalService.updateProduct(product, id, categoryId, supplierId);
    }

    @GetMapping("/batch-status")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = new JobParameters();
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("Status: " + jobExecution.getStatus());
        System.out.println("Spring Batch is running...");

        return jobExecution.getStatus();
    }

}
