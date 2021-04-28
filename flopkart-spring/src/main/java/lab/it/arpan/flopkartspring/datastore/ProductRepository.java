package lab.it.arpan.flopkartspring.datastore;

import java.util.List;
import java.util.Optional;

import lab.it.arpan.flopkartspring.model.Product;

public interface ProductRepository {

    List<Product> getProducts();
    
    Optional<Product> getProduct(Long id);

}
