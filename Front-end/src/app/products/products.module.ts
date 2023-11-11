import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ProductsDetailsComponent } from './Components/products-details/products-details.component';
import { ProductComponent } from './Components/product/product.component';
import { SharedModule } from "../shared/shared.module";



@NgModule({
    declarations: [
    ],
    imports: [
        CommonModule,
        SharedModule
    ]
})
export class ProductsModule { }
