import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';
import { LoginComponent } from '../auth/Componenents/login/login.component';
import { RegisterComponent } from '../auth/Componenents/register/register.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeadersComponent } from './Componenents/headers/headers.component';
import { AllProductsComponent } from '../products/Components/all-products/all-products.component';
import { SpinnerComponent } from './Componenents/spinner/spinner.component';
import { ProductComponent } from '../products/Components/product/product.component';
import { SelectComponent } from './Componenents/select/select.component';
import { ProductsDetailsComponent } from '../products/Components/products-details/products-details.component';
import { CartsComponent } from '../carts/Components/carts/carts.component';
import { SharedService } from './Services/shared.service';



@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    HeadersComponent,
    AllProductsComponent,
    SpinnerComponent,
    ProductComponent,
    SelectComponent,
    ProductsDetailsComponent,
    CartsComponent


  ],
  imports: [
    CommonModule,
    HttpClientModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    

  ],
  exports: [
    HeadersComponent,
    SpinnerComponent,
    SelectComponent,

  ]
})
export class SharedModule { }
