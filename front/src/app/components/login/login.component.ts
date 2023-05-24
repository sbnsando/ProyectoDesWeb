import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { UsersService } from 'src/app/services/users.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit{

  ngOnInit(): void {  }
  errorMessage: string | null = null;


  constructor(private formBuilder: FormBuilder,
    private usersService : UsersService,
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
    this.usersService.login(userParam, passParam)
    .then((response: any) => {
      this.errorMessage = "";
      this.router.navigateByUrl("/home");
      console.log(response);
  }).catch(error => {
    this.errorMessage = error; // Almacena el mensaje de error en la propiedad errorMessage
    console.error(error); // Puedes mostrar un mensaje de error en la consola
  });
    
  }

}
