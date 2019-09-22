package welley.capstone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import welley.capstone.entities.Product;
import welley.capstone.repos.ProductRepository;

import java.util.List;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;


    @Override
    public Product saveProduct(Product product) {

        return repository.save(product);
    }

    @Override
    public Product updateProduct(Product product) {

        return repository.save(product);
    }

    @Override
    public void deleteProduct(Product product) {

        repository.delete(product);
    }

    @Override
    public Product getProductById(int id) {

        return repository.findById(id).get();
    }

    @Override
    public List<Product> getAllProducts() {

        return (List<Product>)repository.findAll();
    }




}
