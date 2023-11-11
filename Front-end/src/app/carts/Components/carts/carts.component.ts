import { Component, OnInit } from '@angular/core';
import { CartsService } from '../../Services/carts.service';

@Component({
  selector: 'app-carts',
  templateUrl: './carts.component.html',
  styleUrls: ['./carts.component.css']
})
export class CartsComponent implements OnInit {

  cartProducts:any[] =[]
  Total:any=0
  success:boolean=false
  constructor(private service:CartsService){}
  ngOnInit(): void {
    this.getCartProduct()
  }

  getCartProduct(){
    if("cart" in localStorage){
      this.cartProducts = JSON.parse(localStorage.getItem("cart")!)
  }
  this.getTotalPrice()
}

getTotalPrice(){
  this.Total=0
 for(let items in this.cartProducts){

this.Total+= this.cartProducts[items].item.price *this.cartProducts[items].quantity;
 }
}

addAmount(index:number){
this.cartProducts[index].quantity++
this.getTotalPrice()
localStorage.setItem("cart" , JSON.stringify(this.cartProducts))

}

minsAmount(index:number){
  this.cartProducts[index].quantity--
  this.getTotalPrice()
  localStorage.setItem("cart" , JSON.stringify(this.cartProducts))


}
DetectAmount(){
  this.getTotalPrice()
  localStorage.setItem("cart" , JSON.stringify(this.cartProducts))
}
DeleteProduct(index:number){
  this.cartProducts.splice(index,1)
  localStorage.setItem("cart" , JSON.stringify(this.cartProducts))

}
ClearCarts(){
  this.cartProducts=[]
  this.getTotalPrice()
  localStorage.setItem("cart" , JSON.stringify(this.cartProducts))

}
addCart(){
  let products = this.cartProducts.map(item =>{
    return {id_product:item.item.id , quantite : item.quantity}
  })
for(let item of products){
  let Model = {
    date:new Date(),
    quantity:item.quantite,
    id_product:item.id_product,
    id_user:5,
  }
  this.service.addToCart(Model).subscribe(response => {
    if (response.status === 200) {
      const responseData = response.body;
      if (responseData && responseData.message === 'success') {
        this.success = true;
      }
    }

  })
}
}
}
