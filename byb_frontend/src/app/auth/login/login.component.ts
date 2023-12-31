import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm : FormGroup;
  constructor(private fb : FormBuilder){
    this.loginForm = fb.group({
      email : ['',[Validators.required, Validators.email]],
      password : ['',Validators.required]
    })
  }

  onSubmit(){
    console.log(this.loginForm.value);
  }
}
