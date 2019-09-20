package welley.capstone.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import welley.capstone.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
