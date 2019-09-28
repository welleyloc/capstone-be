package welley.capstone.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import welley.capstone.entities.Category;
import welley.capstone.service.TransactionalService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryRestController {

    @Autowired
    TransactionalService transactionalService;

    @GetMapping("/getCategories")
    public List<Category> getCategoryDashboard() {
        return transactionalService.getAllCategories();
    }

    @PostMapping("/createCategory")
    public Category createCategory(@RequestBody Category category){
        return transactionalService.saveCategory(category);
    }

}
