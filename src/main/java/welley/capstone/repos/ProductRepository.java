package welley.capstone.repos;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import welley.capstone.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT a FROM Product a WHERE a.category=:category")
    List<Product> findByCategory(@Param("category") String category);

    @Query("SELECT a FROM Product a WHERE a.category=:category and a.availability=:availability")
    List<Product> findByCategoryAndAvailability(@Param("category") String category, @Param("availability") String availability);

}
