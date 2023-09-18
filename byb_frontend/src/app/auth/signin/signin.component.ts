import { Component } from '@angular/core';
import { AbstractControl, FormBuilder, FormControl, FormGroup, ValidatorFn, Validators } from '@angular/forms';

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.scss']
})
export default class SigninComponent {
  signinForm : FormGroup;

  constructor(private fb : FormBuilder){
    this.signinForm = fb.group({
      name : ['',Validators.required],
      email : ['',Validators.required],
      phoneNumber : ['',Validators.required],
      password : ['',Validators.required],
      confirmPassword : ['',Validators.required]
    })
  }


  onSubmit(){
    console.log(this.signinForm.value);
  }
}
