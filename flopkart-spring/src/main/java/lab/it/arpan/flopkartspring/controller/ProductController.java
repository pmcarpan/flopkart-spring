package lab.it.arpan.flopkartspring.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lab.it.arpan.flopkartspring.converter.Converter;
import lab.it.arpan.flopkartspring.datastore.ProductRepository;
import lab.it.arpan.flopkartspring.model.Product;
import lab.it.arpan.flopkartspring.model.response.ProductResponse;

@RestController
@CrossOrigin
public class ProductController {

    private final ProductRepository productRepository;
    private final Converter<Product, ProductResponse> converter;

    @Autowired
    public ProductController(ProductRepository productRepository, Converter<Product, ProductResponse> converter) {
        this.productRepository = productRepository;
        this.converter = converter;
    }

    @GetMapping(path = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProductResponse>> listProducts() {
        return ResponseEntity
                .ok(productRepository.getProducts().stream().map(converter::convert).collect(Collectors.toList()));
    }

    @GetMapping(path = "/products/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ProductResponse> retrieveProduct(@PathVariable Long productId) {
        Product product = productRepository.getProduct(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found."));
        return ResponseEntity.ok(converter.convert(product));
    }

}
