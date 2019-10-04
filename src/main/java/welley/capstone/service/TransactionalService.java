package welley.capstone.service;

import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;

import java.util.List;

public interface TransactionalService {

    // Product methods

    Product createProduct(Product product, int categoryId, int supplierId);

    Product updateProduct(Product product, int id, int categoryId, int supplierId);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();


//  Category methods

    Category createCategory(Category category);

    Category getCategoryById(int categoryId);

    void deleteCategory(Category category);

    Category updateCategory(Category category, int categoryId);

    List<Category> getAllCategories();

    // Supplier methods

    Supplier createSupplier(Supplier supplier);

    Supplier getSupplierById(int supplierId);

    void deleteSupplier(Supplier supplier);

    List<Supplier> getAllSuppliers();

    Supplier updateSupplier(Supplier supplier, int supplierId);

}
