package com.example.demo.RegisterService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.RegisterModel.produit;
import com.example.demo.RegisterReposetry.ProduitRepository;
@Service
public class ProduitService {
   
   private ProduitRepository produitRepository;

@Autowired
public ProduitService (ProduitRepository produitRepository){

this.produitRepository=produitRepository ;
}
public List<produit>getAllproduit(){
   return produitRepository.findAll(); 
}

}
