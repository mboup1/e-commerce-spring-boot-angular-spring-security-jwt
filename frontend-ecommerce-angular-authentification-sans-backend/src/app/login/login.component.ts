import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from '../interfaces/User';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {
  newUserForm!: FormGroup;
  user: User = new User();
  errorMessage: number = 0;
  message: string = "login ou mot de passe erronés..";



  //For register
  // user: User = new User('', '', '', false, []);

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.initForm();
  }

  initForm(): void {
    this.newUserForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onLoggedin() {
    if (this.newUserForm.valid) {
      this.user.username = this.newUserForm.value.username;
      this.user.password = this.newUserForm.value.password;
    }

    console.log(this.user);
    let isValidUser: Boolean = this.authService.SignIn(this.user);
    if (isValidUser)
      this.router.navigate(['/']);
    else
      this.errorMessage = 1;
      // alert('Login ou mot de passe incorrecte!');
  }

  //register
  // onLoggedin(): void {
  //   if (this.newUserForm.valid) {
  //     this.user.username = this.newUserForm.value.username;
  //     this.user.password = this.newUserForm.value.password;

  //     console.log(this.user);

  //     if (this.user.username === 'dame' && this.user.password === '123456') {
  //       // Login successful
  //       console.log('Login successful');
  //       // this.router.navigate(['/']);
  //     } else {
  //       // Login failed
  //       console.log('Invalid credentials');
  //     }
  //   }
  // }
}
