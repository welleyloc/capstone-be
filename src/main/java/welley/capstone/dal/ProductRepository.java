package welley.capstone.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import welley.capstone.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
}
