import { Component } from '@angular/core';
import { Basket } from '../interfaces/basket';
import { BasketService } from '../baskets/service/basket.service';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  basket!: Basket;
  totalPrice!: number;

  constructor(
    private basketService: BasketService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getBasketById();

  }


  getBasketById(): void {
    this.basketService.getBasketById(1).subscribe(
      {
        next: (basket: Basket) => {
          this.basket = basket;

          console.log("basket : ", basket);
        },
        error: (error) => {
          console.error('Error fetching basket:', error);
        }
      }
    );
  }

  reloadBasket(): void {
    window.location.reload(); 
  }


}
