package welley.capstone.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import welley.capstone.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
