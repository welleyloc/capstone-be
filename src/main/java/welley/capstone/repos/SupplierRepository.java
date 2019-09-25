package welley.capstone.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import welley.capstone.entities.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {


}
