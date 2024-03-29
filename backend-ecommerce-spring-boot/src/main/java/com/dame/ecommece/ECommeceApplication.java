package com.dame.ecommece;

import com.dame.ecommece.entity.*;
import com.dame.ecommece.enums.ClientState;
import com.dame.ecommece.enums.OrderState;
import com.dame.ecommece.repository.*;
import com.dame.ecommece.service.ClientService;
import com.dame.ecommece.service.OrderService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.annotation.Bean;
//import org.springframework.web.client.RestTemplate;

import java.util.Date;

@SpringBootApplication
public class ECommeceApplication {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ClientService clientService;

	@Autowired
	private BasketRepository basketRepository;

	@Autowired
	private OrderService orderService;

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

			// Ajouter le client par défaut
			Client initialClient = new Client("Amazon","Jeff","Bezos","jeff@jeff.com","06568584444","124 Amazon",					"456",
					"San Francisco",
					"USA",
					ClientState.ACTIVE
			);

			// Sauvegarder le client initial
			clientService.addClient(initialClient);

			// Créer un panier initial
			Basket basket = new Basket("Panier");
			basketRepository.save(basket);

			//Créer une commande  initiale
			Order order =new Order(1L, OrderState.CONFIRMED);
			orderService.addOrder(order);

//			//Ajouter un article dans le panier
//			// Appeler l'API pour ajouter un article dans le panier
//			String addItemUrl = "http://localhost:8080/api/basket/1/items?basketId=1&productId=2&quantity=3";
//			restTemplate().postForObject(addItemUrl, null, String.class);

		}
	}


//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
}
