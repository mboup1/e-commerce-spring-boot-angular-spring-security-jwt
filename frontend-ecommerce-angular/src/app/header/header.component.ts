import { Component } from '@angular/core';
import { Basket } from '../interfaces/basket';
import { BasketService } from '../baskets/service/basket.service';
import { Route, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrl: './header.component.css'
})
export class HeaderComponent {
  basket!: Basket;
  totalPrice!: number;
  // totalItems!: number;

  constructor(
    private basketService: BasketService,
    private router: Router,
    public authService: AuthService
  ) { }

  ngOnInit(): void {
    this.getBasketById();

    console.log(this.getBasketById())


    const isloggedin = localStorage.getItem('isloggedIn');
    const loggedUser = localStorage.getItem('loggedUser');

    console.log(loggedUser)

    this.authService.loadToken();
    if (this.authService.getToken() == null ||
      this.authService.isTokenExpired())
      this.router.navigate(['/login']);

    //Without backend
    if (isloggedin != "true")
      this.router.navigate(['/login']);
    else
      this.authService.setLoggedUserFromLocalStorage();

  }


  getBasketById(): void {
    this.basketService.getBasketById(1).subscribe(
      {
        next: (basket: Basket) => {
          this.basket = basket;
          console.log('Basket:', this.basket); // Logging basket here

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

  onLogout() {
    this.authService.logout();
    this.clearAllBasketItems()
  }

  clearAllBasketItems() {
    console.log("clear all items");
    this.basketService.clearAllBasketItems().subscribe(
      {
        next: () => {
          console.log('All basket items cleared.');
          this.reloadBasket();
        },
        error: (error) => {
          console.error('Error clearing basket items:', error);
        }
      }
    );
  }

}
