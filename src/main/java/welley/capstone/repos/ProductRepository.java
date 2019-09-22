package welley.capstone.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import welley.capstone.entities.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT a FROM Product a WHERE a.category=:category")
    List<Product> findByCategory(@Param("category") String category);

    @Query("SELECT a FROM Product a WHERE a.category=:category and a.availability=:availability")
    List<Product> findByCategoryAndAvailability(@Param("category") String category, @Param("availability") int availability);
}
