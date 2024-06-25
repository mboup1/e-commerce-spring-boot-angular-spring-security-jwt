import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { BasketService } from '../baskets/service/basket.service';
import { Basket } from '../interfaces/basket';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  basket!: Basket;
  totalPrice: number = 0;
  totalItems: number = 0;

  constructor(
    private basketService: BasketService,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {

    const isloggedin = localStorage.getItem('isloggedIn');
    const loggedUser = localStorage.getItem('loggedUser');

    this.authService.loadToken();
    if (this.authService.getToken() == null || this.authService.isTokenExpired()) {
      this.router.navigate(['/login']);
    }

    // Without backend
    if (isloggedin !== "true") {
      this.router.navigate(['/login']);
    } else {
      this.authService.setLoggedUserFromLocalStorage();
    }

    this.getBasketById();


  }

  getBasketById(): void {
    this.basketService.getBasketById(1).subscribe({
      next: (basket: Basket) => {
        this.basket = basket;
        // console.log('Basket:', this.basket);
      },
      error: (error:HttpErrorResponse) => {
        // console.error('Error fetching basket:', error.message);
      }
    });
  }

  reloadBasket(): void {
    window.location.reload();
  }

  onLogout(): void {
    // this.clearAllBasketItems();
    this.authService.logout();
  }

  clearAllBasketItems(): void {
    this.basketService.clearAllBasketItems().subscribe({
      next: () => {
        console.log('All basket items cleared.');
        this.reloadBasket();
      },
      error: (error) => {
        // console.error('Error clearing basket items:', error);
      }
    });
  }
}
