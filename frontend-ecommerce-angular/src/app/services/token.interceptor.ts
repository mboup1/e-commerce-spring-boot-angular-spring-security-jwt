import { Injectable } from '@angular/core';
import {
  HttpInterceptor,
  HttpHandler,
  HttpRequest,
  HttpEvent
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {

  constructor(private authService: AuthService) { }

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const exclude_array = ["/login", "/register", '/verifyEmail'];

    if (!exclude_array.some(url => request.url.includes(url))) {
      let jwt = this.authService.getToken();
      let reqWithToken = request.clone({
        setHeaders: { Authorization: jwt }
      });
      return next.handle(reqWithToken);
    }
    return next.handle(request);
  }
}
