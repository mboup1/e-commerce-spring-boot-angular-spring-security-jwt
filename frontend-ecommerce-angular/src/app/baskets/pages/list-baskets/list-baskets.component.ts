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
          this.totalPrice = this.basket.totalPrice;
          this.totalItems = this.basket.totalItems;

          console.log("totalItems in ListBasketsComponent : ", this.totalItems);
        },
        error: (error) => {
          console.error('Error fetching basket:', error);
        }
      }
    );
  }



  getTotalPrice(): void {
    this.basketService.getTotalPrice().subscribe({
      next: (totalPrice: number) => {
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

    // console.log('this.basket.basketItems.length :', this.basket.basketItems.length);

    this.basketService.removeItemFromBasket(basketItem).subscribe({
      next: () => {
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

    console.log('Commande passée avec succès!');

  }


}
