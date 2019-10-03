package welley.capstone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import welley.capstone.dal.ProductRepository;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;

import java.util.List;

@SpringBootApplication
public class CapstoneApplication {

    public static void main(String[] args) {
        SpringApplication.run(CapstoneApplication.class, args);
    }

}
