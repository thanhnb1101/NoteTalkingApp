import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { first } from 'rxjs';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: any;
  isLogin = false;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { this.createForm() }

  get f() { return this.loginForm.controls; }
  createForm() {
    this.loginForm = new FormGroup({
      userName: new FormControl(''),
      password: new FormControl('')
    });
  }

  onSubmit() {
    console.log(this.f.userName.value);
    console.log(this.f.password.value);
    this.authenticationService.login(this.f.userName.value, this.f.password.value).pipe(first())
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['list-notes']);
        },
        error => {
          console.log(error);
          this.router.navigate(['login']);
        });
  }
}
