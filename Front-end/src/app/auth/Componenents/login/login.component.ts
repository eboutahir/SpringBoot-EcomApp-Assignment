import { Component, Input } from '@angular/core';
import { AuthService } from '../../Services/auth.service';
import { Router } from '@angular/router';
import { SharedService } from 'src/app/shared/Services/shared.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  user = {
    username: '',
    password: ''
  };



  constructor(private service: AuthService , private router : Router ) {}

  login() {
    this.service.checkLogin(this.user).subscribe(
      (response) => {
        if (response.status === 200) {
          const responseData = response.body;
          if (responseData && responseData.message === 'Connexion réussie.') {
            this.router.navigate(['/product'])

          } else if(responseData && responseData.message === 'Échec de la connexion.'){
            alert('Échec de la connexion');
          }
        }
      },
      (error) => {
        console.error('Erreur lors de la connexion :', error);
      }
    );
}

}
