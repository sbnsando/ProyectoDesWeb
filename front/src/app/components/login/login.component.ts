import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { LoginService } from './login.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  ngOnInit(): void {  }

  constructor(private formBuilder: FormBuilder,
    private loginService : LoginService,
    public router: Router){ }

  checkoutForm = this.formBuilder.group({
    user: '',
    password:''
  })

  onSubmit(){
    let userParam: string;
    let passParam: string;
    userParam = '' + this.checkoutForm.value.user;
    passParam = '' + this.checkoutForm.value.password;
    console.log('form value: ', this.checkoutForm.value);
    console.log('email: ',this.checkoutForm.value.user);
    console.log('Pass: ', this.checkoutForm.value.password);
    this.loginService.login(userParam,passParam).subscribe(
                          data => {
                            console.log(data);
                            this.loginService.setToken(data.token);
                          }
    );
  }

}
