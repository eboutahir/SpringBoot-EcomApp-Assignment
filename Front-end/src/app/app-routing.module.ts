import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './auth/Componenents/login/login.component';
import { RegisterComponent } from './auth/Componenents/register/register.component';
import { AllProductsComponent } from './products/Components/all-products/all-products.component';
import { CartsComponent } from './carts/Components/carts/carts.component';
import { ProductsDetailsComponent } from './products/Components/products-details/products-details.component';

const routes: Routes = [
  {path : "home", component : AllProductsComponent},
  {path : "carts", component : CartsComponent},
  {path : "login", component : LoginComponent},
  {path : "register", component : RegisterComponent},
  {path : "details/:id", component : ProductsDetailsComponent},
  {path : "**", redirectTo:"home" , pathMatch:"full"},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
