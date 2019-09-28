package welley.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;
import welley.capstone.dal.CategoryRepository;
import welley.capstone.dal.ProductRepository;
import welley.capstone.dal.SupplierRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional
public class TransactionalServiceImpl implements TransactionalService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    private Map<Integer, String> categoryMap = new HashMap<>();

    // Product override methods

    @Override
    public Product createProduct(Product product) {
        product.setDiscountPercent();
        product.getDiscountPercent();
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {
        productRepository.delete(product);
    }

    @Override
    public Product getProductById(int id) {
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

    @Override
    public List<Product> sortS(String supplier) {
        List<Product> products = productRepository.sortSupplier(supplier);
        return products;
    }

    @Override
    public List<Product> sortC(String category) {
        List<Product> products = productRepository.sortCategory(category);
        return products;
    }

    @Override
    public List<Product> sortCA(String category, String availability) {
        List<Product> products = productRepository.sortCatAvail(category, availability);
        return products;
    }

    // Category override methods

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public List<Category> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories;
    }

    // Supplier override methods

    @Override
    public Supplier saveSupplier(Supplier supplier) {
        supplierRepository.save(supplier);
        return supplier;
    }

    @Override
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return suppliers;
    }
}