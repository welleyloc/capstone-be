package welley.capstone.service;

import welley.capstone.entities.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Product product);

    Product getProductById(int id);

    List<Product> getAllProducts();

}
