import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../../interfaces/product';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrl: './list-products.component.css'
})
export class ListProductsComponent {
  products: Product[] = [];

  constructor(
    private productService: ProductService,
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
          console.log("products : ", products);
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
    this.router.navigate(['/edit-product', productId]);
  }

  getProduct(productId: number): void {
    // Implémentez votre logique pour afficher les détails du produit, par exemple :
    // this.router.navigate(['/details-product', productId]);
  }

}
