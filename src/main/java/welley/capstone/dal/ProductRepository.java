package welley.capstone.dal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import welley.capstone.entities.Product;

import java.util.List;

@Repository
@CrossOrigin(origins = "http://localhost:4200")
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    @Query("SELECT a FROM Product a WHERE a.supplier=:supplier")
    List<Product> sortSupplier(@Param("supplier") String supplier);

    @Query("SELECT a FROM Product a WHERE a.category=:category")
    List<Product> sortCategory(@Param("category") String category);

    @Query("SELECT a FROM Product a WHERE a.category=:category AND a.availability=:availability")
    List<Product> sortCatAvail(@Param("category") String category, @Param("availability") String availability);


}
