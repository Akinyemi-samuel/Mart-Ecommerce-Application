import { Component, ElementRef, OnInit, Renderer2 } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Users } from 'src/app/model/users';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent {
  form: any;
  user!: Users;
  errormsg!: String;
  formmsg!: String;

  constructor(
    private service: AuthService,
    private router: Router,
    private elRef: ElementRef,
    private renderer: Renderer2
  ) {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),

      password: new FormControl('', [Validators.required,Validators.minLength(2)]),
    });
  }

  get email() {
    return this.form.get('email');
  }

  get password() {
    return this.form.get('password');
  }

  //method for submitting the form input to the server
  submitdetail() {
    if (this.form.valid) {
      this.user = this.form.value;
      this.service.loginUser(this.user).subscribe(
        (data) => {
          //styles the error message variable whwen there is an success
          const div = this.elRef.nativeElement.querySelector('.form_message');
          this.renderer.setStyle(div, 'background-color', 'green');
          this.renderer.setStyle(div, 'color', 'white');
          this.renderer.setStyle(div, 'padding', '15px');
          this.renderer.setStyle(div, 'border-radius', '5px');
          this.renderer.setStyle(div, 'font-family', 'Poppins, sans-serif');
          this.formmsg = 'Login Successful';

          setTimeout(() =>{
            this.router.navigateByUrl('/');
          },2000);
         
         
        },
        (err) => {
          //styles the error message variable whwen there is an error
        const div = this.elRef.nativeElement.querySelector('.form_message');
        this.renderer.setStyle(div, 'background-color', 'red');
        this.renderer.setStyle(div, 'color', 'white');
        this.renderer.setStyle(div, 'padding', '15px');
        this.renderer.setStyle(div, 'border-radius', '5px');
        this.renderer.setStyle(div, 'font-family', 'Poppins, sans-serif');
          
          if(err.status = 404){
            this.formmsg = 'Wrong email or password';
          }
        }
      );
    } else {
      if (this.form.invalid) {
        //styles the error message variable whwen there is an error
        const div = this.elRef.nativeElement.querySelector('.form_message');
        this.renderer.setStyle(div, 'background-color', 'red');
        this.renderer.setStyle(div, 'color', 'white');
        this.renderer.setStyle(div, 'padding', '15px');
        this.renderer.setStyle(div, 'border-radius', '5px');
        this.renderer.setStyle(div, 'font-family', 'Poppins, sans-serif');

        //logs the error to the error variable when there is an error
        if (this.email.errors?.required || this.password.errors?.required) {
          this.formmsg = 'Fields cannot be empty';
        } else if (this.email.errors?.email) {
          this.formmsg = 'Email is invalid';
        } else if (this.password.errors?.minlength) {
          this.formmsg = 'Password must be 5 characters long';
        } else {
          this.formmsg = 'Error';
        }
      }
    }
  }
}
