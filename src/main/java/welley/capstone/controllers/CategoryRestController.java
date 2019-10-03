package welley.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.service.TransactionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryRestController {

    @Autowired
    TransactionalService transactionalService;

    @GetMapping("/getCategory/{categoryId}")
    public Category getCategory(@PathVariable int categoryId) { return transactionalService.getCategoryById(categoryId);}

    @GetMapping("/getCategories")
    public List<Category> getCategoryList() {
        return transactionalService.getAllCategories();
    }

    @GetMapping("/getProductsInCat/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) { return transactionalService.getProductsInCategory(categoryId);}

    @PostMapping("/createCategory")
    public Category createCategory(@RequestBody Category category) {
        return transactionalService.createCategory(category);
    }

    @PutMapping("/updateCategory/{categoryId}")
    public Category updateCategory(String categoryName, @PathVariable int categoryId) {
        return transactionalService.updateCategory(categoryName, categoryId);
    }

    @DeleteMapping("/category/{categoryId}")
    public void deleteCategory(@PathVariable int categoryId) {
        Category category = transactionalService.getCategoryById(categoryId);
        transactionalService.deleteCategory(category);
    }

}
