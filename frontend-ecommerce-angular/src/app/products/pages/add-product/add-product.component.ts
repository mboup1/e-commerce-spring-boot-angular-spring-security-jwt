import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Product } from '../../../interfaces/product';
import { Category } from '../../../interfaces/category';
import { ProductService } from '../../service/product.service';
import { CategorieService } from '../../../categories/service/categorie.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-product',
  templateUrl: './add-product.component.html',
  styleUrls: ['./add-product.component.css']
})
export class AddProductComponent implements OnInit {
  newProductForm!: FormGroup;
  categories: Category[] = [];
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private productService: ProductService,
    private categorieService: CategorieService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForm();
    this.loadCategories();
  }

  initForm(): void {
    this.newProductForm = new FormGroup({
      nameProd: new FormControl('', Validators.required),
      price: new FormControl('', [Validators.required, Validators.min(0)]),
      categoryId: new FormControl('', Validators.required)
    });
  }

  loadCategories(): void {
    this.categorieService.getAllCategories().subscribe(
      (categories: Category[]) => {
        this.categories = categories;
      },
      (error) => {
        console.error('Error fetching categories:', error);
      }
    );
  }

  addProduct(): void {
    if (this.newProductForm.valid) {
      const newProduct: Product = {
        idProd: 0,
        nameProd: this.newProductForm.value.nameProd,
        price: this.newProductForm.value.price,
        date: new Date(),
        category: {
          idCat: this.newProductForm.value.categoryId,
          nameCat: '',
          descriptionCat: ''
        }
      };

      this.productService.addProduct(newProduct).subscribe(
        (response: Product) => {
          this.successMessage = 'Le produit a été ajouté avec succès !';
          this.errorMessage = null;
          this.newProductForm.reset();

          setTimeout(() => {
            this.router.navigate(['/products']);

          }, 3000);
        },
        (error) => {
          this.errorMessage = 'Une erreur s\'est produite lors de l\'ajout du produit.';
          this.successMessage = null;
          console.error('Error adding product:', error);
        }
      );
    }
  }
}
