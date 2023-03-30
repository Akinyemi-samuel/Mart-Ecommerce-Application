import { Component, ElementRef, Renderer2 } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Users } from 'src/app/model/users';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
})
export class RegisterComponent {
  form: any;
  user!: Users;
  formmsg!: String;

  constructor(
    private service: UserService,
    private router: Router,
    private elRef: ElementRef,
    private renderer: Renderer2) {

    this.form = new FormGroup({
      fullName: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
      ]),

      email: new FormControl('', [
        Validators.required,
        Validators.email,
      ]),

      password: new FormControl('', [Validators.required,Validators.minLength(5),]),
    });
  }

  get fullname() {
    return this.form.get('fullName');
  }

  get email() {
    return this.form.get('email');
  }

  get password() {
    return this.form.get('password');
  }

  submitdetail() {
    if (this.form.valid){
      this.user = this.form.value;
      this.service.registerUser(this.user).subscribe((data) => {

        const div = this.elRef.nativeElement.querySelector('.form_message');
        this.renderer.setStyle(div, 'background-color', 'green');
        this.renderer.setStyle(div, 'color', 'white');
        this.renderer.setStyle(div, 'padding', '15px');
        this.renderer.setStyle(div, 'border-radius', '5px');
        this.renderer.setStyle(div, 'font-family', 'Poppins, sans-serif');
        this.formmsg = 'Regsitration Successful';

        setTimeout(() =>{
          this.router.navigateByUrl('/login');
        },2000);

      }, (err)=>{
        const div = this.elRef.nativeElement.querySelector('.form_message');
          this.renderer.setStyle(div, 'background-color', 'red');
          this.renderer.setStyle(div, 'color', 'white');
          this.renderer.setStyle(div, 'padding', '15px');
          this.renderer.setStyle(div, 'border-radius', '5px');
          this.renderer.setStyle(div, 'font-family', 'Poppins, sans-serif');
          if(err.status = 404){
            this.formmsg = 'Email already exists';
          }
          
      }
      );
    }else {
      if (this.form.invalid) {
        //styles the error message variable whwen there is an error
        const div = this.elRef.nativeElement.querySelector('.form_message');
        this.renderer.setStyle(div, 'background-color', 'red');
        this.renderer.setStyle(div, 'color', 'white');
        this.renderer.setStyle(div, 'padding', '15px');
        this.renderer.setStyle(div, 'border-radius', '5px');
        this.renderer.setStyle(div, 'font-family', 'Poppins, sans-serif');

        //logs the error to the error variable when there is an error
        if (this.fullname.errors?.required ||this.email.errors?.required || this.password.errors?.required) {
          this.formmsg = 'Fields cannot be empty';
        } else if (this.email.errors?.email) {
          this.formmsg = 'Email is invalid';
        } else if (this.fullname.errors?.minlength) {
          this.formmsg = 'Password must be 5 characters long';
        }else if (this.password.errors?.minlength) {
          this.formmsg = 'Password must be 5 characters long';
        } else {
          this.formmsg = 'Error';
        }
      }
    }
  
  }
  
}
