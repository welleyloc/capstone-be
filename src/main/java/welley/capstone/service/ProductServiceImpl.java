package welley.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import welley.capstone.entities.Product;
import welley.capstone.repos.ProductRepository;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;


    @Override
    public Product saveProduct(Product product) {

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
        return products;
    }


}
