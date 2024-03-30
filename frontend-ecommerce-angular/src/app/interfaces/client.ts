export interface Client {
  id: number;
  companyName: string;
  firstName: string;
  lastName: string;
  email: string;
  phone: string;
  address: string;
  zipCode: string;
  city: string;
  country: string;
  state: string; // "ACTIVE" or "INACTIVE"
  // orders: Order[];  // Assurez-vous d'avoir une interface Order définie si nécessaire
}
