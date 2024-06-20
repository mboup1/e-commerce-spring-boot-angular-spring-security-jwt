package com.dame.ecommece.service;

import com.dame.ecommece.entity.Product;
import com.dame.ecommece.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Test
    void testGetAllProducts() {
        // Préparation des données de test
        Product product1 = new Product("Tv curvée Samsung", 300.00, new Date(), "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", 5);
        Product product2 = new Product("T-Shirt", 20.00, new Date(), "https://fakestoreapi.com/img/71-3HjGNDUL._AC_SY879._SX._UX._SY._UY_.jpg", 4);

        Mockito.when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<Product> productList = productService.getAllProducts();

        assertEquals(2, productList.size());
    }

    @Test
    void testGetProductById() {

        Long productId = 1L;
        Product product = new Product("Tv curvée Samsung", 300.00, new Date(), "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", 5);
        Mockito.when(productRepository.findById(productId)).thenReturn(java.util.Optional.of(product));

        Product foundProduct = productService.getProductById(productId);

        assertNotNull(foundProduct);
        assertEquals(product.getIdProd(), foundProduct.getIdProd());
    }

    @Test
    void testSaveProduct() {
        Product productToSave = new Product("Tv curvée Samsung", 300.00, new Date(), "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", 5);
        Mockito.when(productRepository.save(productToSave)).thenReturn(productToSave);

        Product savedProduct = productService.saveProduct(productToSave);

        assertNotNull(savedProduct);
        assertEquals(productToSave, savedProduct);
    }

    @Test
    void testUpdateProduct() {
        Long productId = 1L;
        Product existingProduct = new Product("Tv curvée Samsung", 300.00, new Date(), "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", 5);
        Product updatedProduct = new Product("Updated Tv curvée Samsung", 400.00, new Date(), "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", 8);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.of(existingProduct));
        Mockito.when(productRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.updateProduct(productId, updatedProduct);

        assertNotNull(result);
        assertEquals(updatedProduct, result);
    }

    @Test
    void testUpdateProduct_ExistingProductNull() {
        Long productId = 1L;
        Product updatedProduct = new Product("Updated Tv curvée Samsung", 400.00, new Date(), "https://fakestoreapi.com/img/81Zt42ioCgL._AC_SX679_.jpg", 8);

        Mockito.when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Product result = productService.updateProduct(productId, updatedProduct);

        assertNull(result);
    }


    @Test
    void testDeleteProduct() {
        Long productId = 1L;
        Mockito.when(productRepository.existsById(productId)).thenReturn(true);

        boolean result = productService.deleteProduct(productId);

        assertTrue(result);
        verify(productRepository, times(1)).deleteById(productId);
    }

    @Test
    void testDeleteProduct_ProductNotExists() {
        Long productId = 1L;

        Mockito.when(productRepository.existsById(productId)).thenReturn(false);

        boolean result = productService.deleteProduct(productId);

        assertFalse(result);
        verify(productRepository, never()).deleteById(productId);
    }

}