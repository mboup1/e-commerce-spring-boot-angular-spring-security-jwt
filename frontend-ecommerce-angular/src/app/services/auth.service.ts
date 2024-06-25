import { Injectable } from '@angular/core';
import { User } from '../interfaces/User';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BasketService } from '../baskets/service/basket.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiURL: string = 'http://localhost:8081/users';
  token!: string;
  private helper = new JwtHelperService();

  public loggedUser!: string;
  public isloggedIn: Boolean = false;
  public roles!: string[];

  public regitredUser : User = new User();


  constructor(private router: Router,
    private http: HttpClient,
    private basketService: BasketService,

  ) { }

  // Authentication methods
  login(user: User) {
    return this.http.post<User>(this.apiURL + '/login', user, { observe: 'response' });
  }

  logout() {
    this.loggedUser = undefined!;
    this.roles = undefined!;
    this.token = undefined!;
    this.isloggedIn = false;
    localStorage.removeItem('jwt');
    localStorage.removeItem('isloggedIn');
    // this.basketService.clearAllBasketItems().subscribe();
    this.router.navigate(['/login']);
  }

  // Token management methods
  saveToken(jwt: string) {
    localStorage.setItem('jwt', jwt);
    this.isloggedIn = true;
    localStorage.setItem('isloggedIn', String(this.isloggedIn));
    this.token = jwt;
    this.decodeJWT();
  }

  loadToken() {
    this.token = localStorage.getItem('jwt')!;
    this.decodeJWT();
  }

  getToken(): string {
    return this.token;
  }

  decodeJWT() {
    if (this.token == undefined)
      return;
    const decodedToken = this.helper.decodeToken(this.token);
    this.roles = decodedToken.roles;
    this.loggedUser = decodedToken.sub;
  }

  isTokenExpired(): Boolean {
    return this.helper.isTokenExpired(this.token);
  }

  // Role and user management methods
  isAdmin(): Boolean {
    if (!this.roles)
      return false;
    return (this.roles.indexOf('ADMIN') > -1);
  }

  setLoggedUserFromLocalStorage() {
    this.isloggedIn = true;
  }

  registerUser(user: User) {
    return this.http.post<User>(this.apiURL + '/register', user,
      { observe: 'response' });
  }

  setRegistredUser(user : User){
    this.regitredUser=user;
    }

  getRegistredUser(){
  return this.regitredUser;
  }

  validateEmail(code : string){
  return this.http.get<User>(this.apiURL+'/verifyEmail/'+code);
  }

}
