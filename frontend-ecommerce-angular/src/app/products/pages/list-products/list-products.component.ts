import { Component } from '@angular/core';
import { ProductService } from '../../service/product.service';
import { Product } from '../../../interfaces/product';
import { Router } from '@angular/router';
import { BasketItem } from '../../../interfaces/basket-item';
import { BasketService } from '../../../baskets/service/basket.service';
import { AuthService } from '../../../services/auth.service';

@Component({
  selector: 'app-list-products',
  templateUrl: './list-products.component.html',
  styleUrl: './list-products.component.css'
})
export class ListProductsComponent {
  products: Product[] = [];


  constructor(
    private productService: ProductService,
    private basketService: BasketService,
    public authService: AuthService,

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
        },
        error: (error) => {
          // console.error('Error fetching products:', error);
        }
      }
    );
  }


  onDeleteProduct(productId: number, productName: string): void {

    let conf = confirm(`Are you sure you want to delete ${productName} ?`);
    if (conf) {
      this.productService.deleteProduct(productId).subscribe(() => {
        this.products = this.products.filter(product => product.idProd !== productId);
      }, error => {
        console.error("Error deleting product : ", error);
      });
    }
  }

  onEditProduct(productId: number): void {
    console.log("productId : ", productId)
    this.router.navigate(['products/edit-product', productId]);
  }




  addItemToBasket(productID: number, quantity: number): void {
    const basketItem: BasketItem = this.createBasketItem(productID, quantity);

    this.basketService.addItemToBasket(basketItem).subscribe({
      next: (addedItem: BasketItem) => {
        console.log('Item added to basket:', addedItem);
        this.router.navigate(['/basket']);
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

}
