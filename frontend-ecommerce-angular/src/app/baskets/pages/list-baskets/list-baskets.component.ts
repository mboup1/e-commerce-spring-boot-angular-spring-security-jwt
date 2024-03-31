import { Component, OnInit } from '@angular/core';
import { BasketService } from '../../service/basket.service';
import { Basket } from '../../../interfaces/basket';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-baskets',
  templateUrl: './list-baskets.component.html',
  styleUrls: ['./list-baskets.component.css']
})
export class ListBasketsComponent implements OnInit {
  baskets: Basket[] = [];

  constructor(
    private basketService: BasketService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getAllBaskets();
  }

  getAllBaskets(): void {
    this.basketService.getAllBaskets().subscribe(
      {
        next: (baskets: Basket[]) => {
          this.baskets = baskets;
          console.log("baskets : ", baskets);
        },
        error: (error) => {
          console.error('Error fetching baskets:', error);
        }
      }
    );
  }


  onDeleteBasket(basketId: number, basketName: string): void {
    console.log(basketId)
    // Implémentez votre logique pour supprimer le panier, par exemple :
    let conf = confirm(`Etes-vous sûr de vouloir supprimer ${basketName} ?`);
    if (conf) {
      this.basketService.deleteBasket(basketId).subscribe(() => {
        this.baskets = this.baskets.filter(basket => basket.id !== basketId);
        console.log("Panier supprimé avec succès!");
      }, error => {
        console.error("Erreur lors de la suppression du panier:", error);
      });
    }
  }

  onEditBasket(basketId: number): void {
    console.log("basketId : ", basketId)
    // Implémentez votre logique pour rediriger vers la page d'édition du panier, par exemple :
    this.router.navigate(['/edit-basket', basketId]);
  }

  getBasket(basketId: number): void {
    // Implémentez votre logique pour afficher les détails du panier, par exemple :
    // this.router.navigate(['/details-basket', basketId]);
  }
}
