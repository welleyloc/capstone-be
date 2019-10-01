package welley.capstone.writers;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import welley.capstone.dal.CategoryRepository;
import welley.capstone.dal.ProductRepository;
import welley.capstone.dal.SupplierRepository;
import welley.capstone.entities.Category;
import welley.capstone.entities.Product;
import welley.capstone.entities.Supplier;

import java.util.List;

@Component
public class ProductWriter implements ItemWriter<Product> {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Override
    public void write(List<? extends Product> products) throws Exception {

        // get all categories/suppliers in a list for column mapping
        List<Category> categories = categoryRepository.findAll();
        List<Supplier> suppliers = supplierRepository.findAll();

        // start filling in categories by foreign key:
        // loop over all the products
        System.out.println("start loop");

        for (Product product : products) {
            System.out.println(product.getCategory());
            // loop over all categories that might be associated with product
            for (Category category : categories) {
                System.out.println("Current Category: " + category);

                // get Product category foreign key
                int product_category_fk = Integer.parseInt(product.getCategory().getCategoryName());

                System.out.println("Current Product category foreign key id is: " + product_category_fk);

                // if foreign key matches the Category ID then map and break
                if (category.getCategoryId() == product_category_fk) {
                    product.setCategory(category);
                    System.out.println("Product set to this category: " + category);
                    break; // out of category block
                }
            }

            for (Supplier supplier : suppliers) {
                int product_supplier_fk = Integer.parseInt(product.getSupplier().getSupplierName());

                if (supplier.getSupplierId() == product_supplier_fk) {
                    product.setSupplier(supplier);
                    break;
                }
            }
        }
        System.out.println("Products saved: " + products);
        productRepository.saveAll(products);
    }
}



