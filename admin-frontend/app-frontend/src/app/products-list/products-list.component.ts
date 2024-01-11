import {Component, OnInit} from '@angular/core';
import {ProduitService, ProduitsResultListDTO} from "../../lib/openapi";

@Component({
  selector: 'app-products-list',
  templateUrl: './products-list.component.html',
  styleUrls: ['./products-list.component.css']
})
export class ProductsListComponent implements OnInit{

  public productList : ProduitsResultListDTO = {};

  constructor(private produitService : ProduitService) {
  }

  ngOnInit(): void {
    this.getProducts();
  }

  getProducts(){
    this.produitService.getAllProducts(1, 10).subscribe({
      next : value => {
        this.productList = value;
        console.log(value);
      },
      error : err => {
        console.log(err);
      }
    });
  }
}
