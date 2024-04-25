import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../service/basket.service';
import { Basket } from '../../../interfaces/basket';
import { Router } from '@angular/router';
import { BasketItem } from '../../../interfaces/basket-item';

@Component({
  selector: 'app-list-baskets',
  templateUrl: './list-baskets.component.html',
  styleUrls: ['./list-baskets.component.css']
})
export class ListBasketsComponent implements OnInit {
  basket!: Basket;
  totalPrice!: number;
  totalItems: number = 0;



  constructor(
    private basketService: BasketService,
    private router: Router,

  ) { }

  ngOnInit(): void {
    this.getBasketById();
    this.getTotalPrice();

  }



  getBasketById(): void {
    this.basketService.getBasketById(1).subscribe(
      {
        next: (basket: Basket) => {
          this.basket = basket;

          // console.log("basket : ", basket);
        },
        error: (error) => {
          console.error('Error fetching basket:', error);
        }
      }
    );
  }



  getTotalPrice(): void {
    this.basketService.getTotalPrice().subscribe({
      next: (totalPrice: number) => { // Modifier le type de retour ici aussi
        this.totalPrice = totalPrice;
        // console.log("Total Price: ", totalPrice);
      },
      error: (error) => {
        console.error('Error fetching total price:', error);
      }
    });
  }

  addItemToBasket(productID: number, quantity: number): void {
    const basketItem: BasketItem = this.createBasketItem(productID, quantity);

    this.basketService.addItemToBasket(basketItem).subscribe({
      next: (addedItem: BasketItem) => {
        // console.log('Item added to basket:', addedItem);
        this.getBasketById();
        this.getTotalPrice();
      },
      error: (error) => {
        console.error('Error adding item to basket:', error);
      }
    });
  }


  removeItemFromBasket(productID: number, quantity: number): void {
    const basketItem: BasketItem = this.createBasketItem(productID, quantity);

    console.log('this.basket.basketItems.length :', this.basket.basketItems.length);
    // console.log('basketItem :', basketItem);

    this.basketService.removeItemFromBasket(basketItem).subscribe({
    next: () => {
      // console.log('Item removed from basket successfully');
      this.getBasketById();
      this.getTotalPrice();
    },
    error: (error) => {
      console.error('Error removing item from basket:', error);
    }
  });
  }



  createBasketItem(productID: number, quantity: number): BasketItem {
    const basketItem: BasketItem = {
      basket: { id: 1, nameBasket: '', totalPrice: 0, totalItems: 0, basketItems: [] },
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

  validateOrder(): void {
    this.router.navigate(['/orders']);

    // Mettez ici la logique pour passer la commande
    console.log('Commande passée avec succès!');



  }



  // removeItemFromBasket(productID: number, quantity: number): void {
  //   const basketItem: BasketItem = this.createBasketItem(productID, quantity);

  //   // Vérifier si la quantité du produit est égale à 1
  //   const productIndex = this.basket.basketItems.findIndex((item: BasketItem) => item.product.idProd === productID);
  //   if (productIndex !== -1 && this.basket.basketItems[productIndex].quantity === 1) {
  //     const confirmation = window.confirm("Voulez-vous vraiment supprimer ce produit ?");
  //     if (confirmation) {
  //       this.removeItemFromBasketService(basketItem);
  //     }
  //   } else {
  //     this.removeItemFromBasketService(basketItem);
  //   }
  // }

  // private removeItemFromBasketService(basketItem: BasketItem): void {
  //   this.basketService.removeItemFromBasket(basketItem).subscribe({
  //     next: () => {
  //       // console.log('Item removed from basket successfully');
  //       this.getBasketById();
  //       this.getTotalPrice();
  //     },
  //     error: (error) => {
  //       console.error('Error removing item from basket:', error);
  //     }
  //   });
  // }


}
