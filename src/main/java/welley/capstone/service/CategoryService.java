package welley.capstone.service;

import org.springframework.stereotype.Service;
import welley.capstone.entities.Category;

import java.util.List;

public interface CategoryService {

    public Category saveCategory(Category category);

    public List<Category> getAllCategories();

}
