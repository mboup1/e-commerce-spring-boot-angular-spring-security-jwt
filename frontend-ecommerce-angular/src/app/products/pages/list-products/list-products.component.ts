import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../../interfaces/product';
import { Router } from '@angular/router';
import { BasketItem } from '../../../interfaces/basket-item';
import { BasketService } from '../../../baskets/service/basket.service';
import { Basket } from '../../../interfaces/basket';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrl: './list-products.component.css'
})
export class ListProductsComponent {
  products: Product[] = [];
  // basket!: Basket;


  constructor(
    private productService: ProductService,
    private basketService: BasketService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllProducts();
  }

  getAllProducts(): void {
    this.productService.getAllProducts().subscribe(
      {
        next: (products: Product[]) => {
          this.products = products;
          // console.log("products : ", products);
        },
        error: (error) => {
          console.error('Error fetching products:', error);
        }
      }
    );
  }


  onDeleteProduct(productId: number, productName: string): void {
    console.log(productId)
    // Implémentez votre logique pour supprimer le produit, par exemple :
    let conf = confirm(`Etes-vous sûr de vouloir supprimer ${productName} ?`);
    if (conf) {
      this.productService.deleteProduct(productId).subscribe(() => {
        this.products = this.products.filter(product => product.idProd !== productId);
        console.log("Product supprimé avec succès!");
      }, error => {
        console.error("Erreur lors de la suppression du produit:", error);
      });
    }
  }

  onEditProduct(productId: number): void {
    console.log("productId : ", productId)
    // Implémentez votre logique pour rediriger vers la page d'édition du produit, par exemple :
    this.router.navigate(['products/edit-product', productId]);
  }




  addItemToBasket(productID: number, quantity: number): void {
    const basketItem: BasketItem = this.createBasketItem(productID, quantity);

    this.basketService.addItemToBasket(basketItem).subscribe({
      next: (addedItem: BasketItem) => {
        console.log('Item added to basket:', addedItem);
        this.router.navigate(['/basket']);

        // this.getBasketById();
        // this.getTotalPrice();
      },
      error: (error) => {
        console.error('Error adding item to basket:', error);
      }
    });
  }


  createBasketItem(productID: number, quantity: number): BasketItem {
    const basketItem: BasketItem = {
      basket: { id: 1, nameBasket: '', totalItems: 0, totalPrice: 0, basketItems: [] },
      product: {
        idProd: productID,
        category: { idCat: 1, nameCat: "Category Name", descriptionCat: "Category Description" },
        nameProd: '',
        imageUrl: '',
        price: 0,
        rating: 0,
        date: new Date()
      },
      quantity: quantity,
      id: 0,
      totalExcludeTaxe: 0,
      totalWithTaxe: 0
    };
    return basketItem;
  }

  truncateText(text:string, maxLength:number) {
    if (text.length <= maxLength) {
      return text;
    } else {
      return text.slice(0, maxLength) + '...';
    }
  }

  generateStarRating(rating:number) {
    const maxStars = 5;
    const fullStars = Math.floor(rating);
    const halfStar = rating % 1 !== 0;
    const emptyStars = maxStars - fullStars - (halfStar ? 1 : 0);
    const stars = '★'.repeat(fullStars) + (halfStar ? '½' : '') + '☆'.repeat(emptyStars);
    return stars;
  }


  // getBasketById(): void {
  //   this.basketService.getBasketById(1).subscribe(
  //     {
  //       next: (basket: Basket) => {
  //         this.basket = basket;
  //         // console.log("basket : ", basket);
  //       },
  //       error: (error) => {
  //         console.error('Error fetching basket:', error);
  //       }
  //     }
  //   );
  // }

  // getTotalPrice(): void {
  //   this.basketService.getTotalPrice().subscribe({
  //     next: (totalPrice: number) => { // Modifier le type de retour ici aussi
  //       this.totalPrice = totalPrice;
  //       // console.log("Total Price: ", totalPrice);
  //     },
  //     error: (error) => {
  //       console.error('Error fetching total price:', error);
  //     }
  //   });
  // }


  getProduct(productId: number): void {
    // Implémentez votre logique pour afficher les détails du produit, par exemple :
    // this.router.navigate(['/details-product', productId]);
  }

}
