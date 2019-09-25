package welley.capstone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import welley.capstone.entities.Category;
import welley.capstone.service.CategoryService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryRestController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/getCategories")
    public List<Category> getCategoryDashboard() {
        return categoryService.getAllCategories();
    }

    @PostMapping("/createCategory")
    public Category createCategory(Category category){
        return categoryService.saveCategory(category);
    }

}
