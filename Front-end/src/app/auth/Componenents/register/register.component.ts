import { Component } from '@angular/core';
import { AuthService } from '../../Services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = {
    username: '',
    password: '',
    email: '',
    tel: ''
  };

  message = '';

  constructor(private service: AuthService , private router : Router) {}

  register() {
    this.service.checkRegister(this.user).subscribe(
      (response) => {
        if (response.status === 200) {
          const responseData = response.body;
          if (responseData && responseData.message === 'Registration réussite.') {
            this.router.navigate(['/login']);
          } else {
            alert('Échec de la registration');
          }
        }
      },
      (error) => {
        console.error('Erreur lors de la connexion :', error);
      }
    );
}
}
