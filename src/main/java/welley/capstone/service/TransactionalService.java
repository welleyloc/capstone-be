package welley.capstone.service;

import org.hibernate.validator.constraints.Mod11Check;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface TransactionalService {

    // Product methods

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    Category createCategory(Category category);

//    Product createProductInCategory(Product product);

    Category deleteCategory(Category category);

    Category updateCategory(Category category);

    List<Category> getAllCategories();


    // Supplier methods

   Supplier createSupplier(Supplier supplier);

   List<Supplier> getAllSuppliers();

}
