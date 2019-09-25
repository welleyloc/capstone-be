package welley.capstone.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import welley.capstone.entities.Product;
import welley.capstone.repos.ProductRepository;
import welley.capstone.service.ProductService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductRestController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job job;

    @Autowired
    ProductService productService;

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
        List<Product> products = productService.getAllProducts();
        return products;
    }

    @GetMapping("/{id}")
    public Product getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("/sortCat")
    public List<Product> sortCategory(@RequestParam(required=false, defaultValue = "") String category) {
        List<Product> products = productService.sortC(category);
        return products;
    }

    @GetMapping("/sortSup")
    public List<Product> sortSupplier(@RequestParam(required=false, defaultValue = "") String supplier) {
        List<Product> products = productService.sortS(supplier);
        return products;
    }

    @GetMapping("/sortCatAvail")
    public List<Product> sortCategoryAvailability(@RequestParam(required=false, defaultValue = "") String category,
                                                  @RequestParam(required=false, defaultValue = "") String availability) {
        List<Product> products = productService.sortCA(category, availability);
        return products;
    }

    @DeleteMapping("/{id}")
    public void deleteProd(@PathVariable int id) {
        Product product = productService.getProductById(id);
        productService.deleteProduct(product);
    }

    @PostMapping("/createProduct")
    public Product createProd(Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/updateProduct")
    public Product updateProd(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

// For @Controller with Thymeleaf - not entirely complete

//    @GetMapping("deleteProduct")
//    public String removeProduct(@RequestParam("id") int id) {
//        Product product = productService.getProductById(id);
//        productService.deleteProduct(product);
//        return "dashboard";
//    }
//
//    @GetMapping("/showNewProductModal")
//    public String newProduct() {
//        return "newModal";
//    }
//
//    @GetMapping("/saveProduct")
//    public String saveProduct(@ModelAttribute("product") Product product, Model model) {
//        Product savedProduct = productService.saveProduct(product);
//        String message = "Saved product id# " + savedProduct.getId() + ", " + savedProduct.getProductName();
//        model.addAttribute("message", message);
//        return "newModal";
//    }
//
//    @GetMapping("/updateProduct")
//    public String showEdit(@RequestParam("id") int id, Model model) {
//        Product product = productService.getProductById(id);
//        model.addAttribute("product", product);
//        return "editProduct";
//    }
//
//    @GetMapping("/updateProd")
//    public String saveUpdate(@ModelAttribute("product") Product product, Model model) {
//        Product updatedProduct = productService.updateProduct(product);
//
//        List<Product> products = productService.getAllProducts();
//        model.addAttribute("products", products);
//
//        String message = "Updated " + updatedProduct.getProductName();
//        model.addAttribute("message", message);
//        return "dashboard";
//    }
}
