package com.dame.ecommece;

import com.dame.ecommece.entity.Basket;
import com.dame.ecommece.entity.Category;
import com.dame.ecommece.entity.Product;
import com.dame.ecommece.repository.BasketRepository;
import com.dame.ecommece.repository.CategoryRepository;
import com.dame.ecommece.repository.ProductRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class ECommeceApplication {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private BasketRepository basketRepository;

	public static void main(String[] args) {
		SpringApplication.run(ECommeceApplication.class, args);
	}

	@PostConstruct
	void init_products() {
		if (productRepository.count() == 0) {
		// Ajouter trois catégories
		Category category1 = new Category("Téléphonie", "Appareils électroniques de communication");
		Category category2 = new Category("Télévision", "Appareils de diffusion audiovisuelle");
		Category category3 = new Category("Ordinateur", "Dispositifs informatiques");

		categoryRepository.save(category1);
		categoryRepository.save(category2);
		categoryRepository.save(category3);

		// Ajouter trois produits
		Product product1 = new Product("Smartphone", 500.00, new Date());
		product1.setCategory(category1);
		Product product2 = new Product("T-Shirt", 20.00, new Date());
		product2.setCategory(category2);
		Product product3 = new Product("Java Programming Book", 30.00, new Date());
		product3.setCategory(category3);

		productRepository.save(product1);
		productRepository.save(product2);
		productRepository.save(product3);


			// Créer un panier initial
			Basket basket = new Basket("Panier");
			basketRepository.save(basket);
		}
	}
}
