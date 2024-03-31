import { Component, OnInit } from '@angular/core';
import { OrderService } from '../../service/order.service';
import { Order } from '../../../interfaces/order';
import { BasketService } from '../../../baskets/service/basket.service';
import { Basket } from '../../../interfaces/basket';

@Component({
  selector: 'app-list-orders',
  templateUrl: './list-orders.component.html',
  styleUrl: './list-orders.component.css'
})
export class ListOrdersComponent implements OnInit {
  orders: Order[] = [];
  totalPrice!: number;

  constructor(
    private orderService: OrderService,
    private basketService: BasketService,
  ) { }

  ngOnInit(): void {
    this.getAllOrders();
    this.getTotalPrice(); // Appeler la méthode pour récupérer le prix total

  }

  getAllOrders(): void {
    this.orderService.getAllOrders().subscribe({
      next: (orders: Order[]) => {
        this.orders = orders;
        console.log("orders: ", orders);
      },
      error: (error) => {
        console.error('Error fetching orders:', error);
      }
    });
  }

  getTotalPrice(): void {
    this.basketService.getTotalPrice().subscribe({
      next: (totalPrice: number) => { // Modifier le type de retour ici aussi
        this.totalPrice = totalPrice;
        console.log("Total Price: ", totalPrice);
      },
      error: (error) => {
        console.error('Error fetching total price:', error);
      }
    });
  }

  viewOrderDetails(orderId: number): void{

  }

  editOrder(orderId: number): void {

  }

  deleteOrder(orderId: number): void {

  }
}
