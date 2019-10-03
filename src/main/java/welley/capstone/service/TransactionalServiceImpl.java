package welley.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.tools.jconsole.JConsole;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;
import welley.capstone.dal.CategoryRepository;
import welley.capstone.dal.ProductRepository;
import welley.capstone.dal.SupplierRepository;

import java.util.*;


@Service
@Transactional
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    // Product override methods

    @Override
    public Product createProduct(Product product, int categoryId, int supplierId) {
        product.setDiscountPercent();
        product.getDiscountPercent();

        Category category = categoryRepository.findById(categoryId).get();
        category.addProductToList(product);

        Supplier supplier = supplierRepository.findById(supplierId).get();
        supplier.addProductToList(product);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product, int id, int categoryId, int supplierId) {

        product.setDiscountPercent();
        product.getDiscountPercent();

        Category category = categoryRepository.findById(categoryId).get();
        product.setCategory(category);

        Supplier supplier = supplierRepository.findById(supplierId).get();
        product.setSupplier(supplier);
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(int id) {
        getAllProducts();
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();

        for (Product product : products) {
            product.setDiscountPercent();
            product.getDiscountPercent();
        }
        return products;
    }

    // Category override methods


    @Override
    public Category getCategoryById(int categoryId) {
        return categoryRepository.findById(categoryId).get();
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Category category) {
        categoryRepository.delete(category);
    }

    @Override
    public Category updateCategory(String categoryName, int categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        category.setCategoryName(categoryName);
        getAllProducts();
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        getAllProducts();
        return categories;
    }

    @Override
    public List<Product> getProductsInCategory(int categoryId) {
        Category category = categoryRepository.findById(categoryId).get();
        List<Product> products = category.getProductList();
        getAllProducts();
        return products;
    }

    // Supplier override methods


    @Override
    public Supplier getSupplierById(int supplierId) {
        return supplierRepository.findById(supplierId).get();
    }

    @Override
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }

    @Override
    public Supplier updateSupplier(String supplierName, int supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).get();
        supplier.setSupplierName(supplierName);
        getAllProducts();
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier createSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        getAllProducts();
        return suppliers;
    }

    @Override
    public List<Product> getProductsInSupplier(int supplierId) {
        Supplier supplier = supplierRepository.findById(supplierId).get();
        List<Product> products = supplier.getProductList();
        getAllProducts();
        return products;
    }
}
