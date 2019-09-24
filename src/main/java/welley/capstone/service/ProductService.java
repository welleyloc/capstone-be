package welley.capstone.service;

import welley.capstone.entities.Product;

import java.util.List;

public interface ProductService {

    public Product saveProduct(Product product);

    public Product updateProduct(Product product);

    public void deleteProduct(Product product);

    public Product getProductById(int id);

    public List<Product> getAllProducts();

    public List<Product> sortC(String category);

    public List<Product> sortCA(String category, String availability);

}
