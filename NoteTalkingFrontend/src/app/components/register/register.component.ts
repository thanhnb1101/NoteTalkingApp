import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { AuthenticationService } from 'src/app/services/authentication.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  registerForm: any;
  userReg: User;

  constructor(
    private router: Router,
    private authenticationService: AuthenticationService
  ) { this.createForm() }

  get f() { return this.registerForm.controls; }
  createForm() {
    this.registerForm = new FormGroup({
      userName: new FormControl(''),
      pwd: new FormControl('')
    });
  }

  onSubmit() {
    this.userReg = this.registerForm.value;
    console.log(this.userReg);
    this.authenticationService.register(this.userReg)
      .subscribe(
        data => {
          console.log(data);
          this.router.navigate(['login']);
        },
        error => {
          console.log(error);
          this.router.navigate(['register']);
        });
    this.router.navigate(['list-notes']);
  }
}
