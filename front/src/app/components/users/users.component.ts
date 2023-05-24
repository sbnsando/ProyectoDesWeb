import { Component } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent {

  users: any = [];

  constructor(private authService: AuthService){
   this.authService.getAllUsers()
   .then(data => {
      this.users = data;
      this.users = this.users.filter((user: any) => user.active === true);

    }); 
  }

  deleteUser(id: number) {
    this.authService.deleteUser(id)
      .then(() => {
        window.location.reload();
      alert('Usario eliminado');  
      });
      
  }

}
