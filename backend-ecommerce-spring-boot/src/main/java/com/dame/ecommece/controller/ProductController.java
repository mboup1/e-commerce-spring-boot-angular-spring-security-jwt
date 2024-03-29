package com.dame.ecommece.controller;

import com.dame.ecommece.entity.Product;
import com.dame.ecommece.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.ok("No products with this id !");
        }
    }


    @PostMapping
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        Product addProduct = productService.saveProduct(product);
        return ResponseEntity.ok(addProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.getProductById(id);
        if (existingProduct != null) {
            product.setIdProd(id);
            Product updateProduct = productService.updateProduct(id, product);
            return ResponseEntity.ok(updateProduct);

        } else {
            return ResponseEntity.badRequest().body("No products with this id !");        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.ok("Product successfully removed !");
        } else {
            return ResponseEntity.ok("No products with this id !");
        }

    }
}
