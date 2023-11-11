import { Component, OnInit } from '@angular/core';
import { ProductsService } from '../../Services/products.service';

@Component({
  selector: 'app-all-products',
  templateUrl: './all-products.component.html',
  styleUrls: ['./all-products.component.css']
})
export class AllProductsComponent implements OnInit {

  products : any[]=[]
  categories : any[]=[]
  spinner : boolean = false
  cartProducts : any[] = []
  constructor(private service:ProductsService){}


  ngOnInit(): void{
       this.getProducts()
       this.getCategories()
  }
  getProducts(){
    this.spinner = true
    return this.service.getAllProducts().subscribe((res:any) =>{
          this.spinner = false
           this.products = res

    }, error => {
      alert(error)
    })
  }
  getCategories(){
    this.spinner = true
    return this.service.getAllCategories().subscribe((res:any) =>{
           this.spinner = false
           this.categories = res
    }, error => {
      alert(error)
    })
  }
filterCategory(event:any){
let value = event.target.value
if(value != 'all'){
this.getProductsByCategories(value)}else{
   this.getProducts();
}
}

getProductsByCategories(kyword:string){
  this.spinner = true
  this.service.getProductsByCategories(kyword).subscribe((res:any) => {
    this.spinner = false
    this.products=res
  }, error => {
    alert(error)
  })
}

addToCart(event:any){
if("cart" in localStorage){
  this.cartProducts = JSON.parse(localStorage.getItem("cart")!)
  let exist = this.cartProducts.find(item => item.item.id == event.item.id)
  if(exist){
    alert("Product is already in your cart")
  }else{
    this.cartProducts.push(event)
    localStorage.setItem("cart" , JSON.stringify(this.cartProducts))
  }
}else{
  this.cartProducts.push(event)
    localStorage.setItem("cart" , JSON.stringify(this.cartProducts))
}
}


}
