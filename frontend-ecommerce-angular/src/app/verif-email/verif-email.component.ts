import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../interfaces/User';
import { AuthService } from '../services/auth.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-verif-email',
  templateUrl: './verif-email.component.html',
  styleUrls: ['./verif-email.component.css']
})
export class VerifEmailComponent implements OnInit {
  verifyForm!: FormGroup;
  user: User = new User();
  err: string = "";

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router,
    private toastr: ToastrService

  ) { }

  ngOnInit(): void {
    this.verifyForm = this.formBuilder.group({
      code: ['', Validators.required]
    });
    this.user = this.authService.regitredUser;
  }

  onValidateEmail() {
    if (this.verifyForm.invalid) {
      this.err = "Le code est obligatoire.";
      return;
    }
    const code = this.verifyForm.get('code')?.value;
    this.authService.validateEmail(code).subscribe({
      next: (res) => {
        this.toastr.success('Connexion réussie !', 'Succès');

        this.authService.login(this.user).subscribe({
          next: (data) => {
            let jwToken = data.headers.get('Authorization')!;
            this.authService.saveToken(jwToken);
            this.router.navigate(['/']);
          },
          error: (err: any) => {
            console.log(err);
          }
        });
      },
      error: (err: any) => {
        if ((err.error.errorCode == "INVALID_TOKEN"))
            this.err = "Votre code n'est pas valide !";

        if ((err.error.errorCode == "EXPIRED_TOKEN"))
            this.err = "Votre code a expiré !";
      }
      // error: (err: any) => {
      //   if (err.status === 400) {
      //     this.err = err.error.message;
      //   }
      //   console.log(err);
      // }
    });
  }
}
