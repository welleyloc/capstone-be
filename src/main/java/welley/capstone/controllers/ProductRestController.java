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

    @GetMapping("/batch-status")
    public BatchStatus load() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters parameters = new JobParameters();
        JobExecution jobExecution = jobLauncher.run(job, parameters);

        System.out.println("Status: " + jobExecution.getStatus());
        System.out.println("Spring Batch is running...");

        return jobExecution.getStatus();
    }

    @GetMapping("/dashboard")
    public List<Product> getDashboard() {
        return transactionalService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {

        return transactionalService.getProductById(id);
    }
//
//    @GetMapping("/sortCat")
//    public List<Product> sortCategory(@RequestParam(required=false, defaultValue = "") String category) {
//        return transactionalService.sortC(category);
//    }
//
//    @GetMapping("/sortSup")
//    public List<Product> sortSupplier(@RequestParam(required=false, defaultValue = "") String supplier) {
//        return transactionalService.sortS(supplier);
//    }
//
//    @GetMapping("/sortCatAvail")
//    public List<Product> sortCategoryAvailability(@RequestParam(required=true, defaultValue = "") String category,
//                                                  @RequestParam(required=true, defaultValue = "") String availability) {
//        return transactionalService.sortCA(category, availability);
//    }

    @DeleteMapping("/{id}")
    public void deleteProd(@PathVariable int id) {
        Product product = transactionalService.getProductById(id);
        transactionalService.deleteProduct(product);
    }

    @PostMapping("/createProduct/{categoryId}")
    public Product createProd(@RequestBody Product product) {
        return transactionalService.createProduct(product);
    }

//    @PostMapping("/createProduct")
//    public Product createProd(@RequestBody Product product) {
//        return transactionalService.createProduct(product);
//    }

    @PutMapping("/updateProduct")
    public Product updateProd(@RequestBody Product product) {
        return transactionalService.updateProduct(product);
    }

}
