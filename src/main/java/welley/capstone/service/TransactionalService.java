package welley.capstone.service;

import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;

import java.util.List;

public interface TransactionalService {

    // Product methods

    Product createProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

    List<Product> sortS(String supplier);

    List<Product> sortC(String category);

    List<Product> sortCA(String category, String availability);

    // Category methods

    Category saveCategory(Category category);

    List<Category> getAllCategories();

    // Supplier methods

   Supplier saveSupplier(Supplier supplier);

   List<Supplier> getAllSuppliers();

}
