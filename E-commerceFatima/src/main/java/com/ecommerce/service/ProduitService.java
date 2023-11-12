package com.ecommerce.service;

import com.ecommerce.entity.Cart;
import com.ecommerce.entity.Produit;
import com.ecommerce.entity.User;
import com.ecommerce.configuration.JwtRequestFilter;
import com.ecommerce.dao.CartDao;
import com.ecommerce.dao.ProduitDao;
import com.ecommerce.dao.UtilisateurDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProduitService {

    @Autowired
    private ProduitDao produitDao;

    @Autowired
    private UtilisateurDao utilisateurDao;

    @Autowired
    private CartDao cartDao;

    public Produit addNewProduct(Produit produit) {
        return produitDao.save(produit);
    }

    public List<Produit> getAllProducts(int pageNumber, String searchKey) {
        Pageable pageable = PageRequest.of(pageNumber,12);

        if(searchKey.equals("")) {
            return (List<Produit>) produitDao.findAll(pageable);
        } else {
            return (List<Produit>) produitDao.findByProductNameContainingIgnoreCaseOrProductDescriptionContainingIgnoreCase(
                    searchKey, searchKey, pageable
            );
        }

    }

    public Produit getProductDetailsById(Integer productId) {
        return produitDao.findById(productId).get();
    }

    public void deleteProductDetails(Integer productId) {
        produitDao.deleteById(productId);
    }

    public List<Produit> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        if(isSingleProductCheckout && productId != 0) {
            // we are going to buy a single product

            List<Produit> list = new ArrayList<>();
            Produit produit = produitDao.findById(productId).get();
            list.add(produit);
            return list;
        } else {
            // we are going to checkout entire cart
            String username = JwtRequestFilter.CURRENT_USER;
            User user = utilisateurDao.findById(username).get();
            List<Cart> carts = cartDao.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }
    }
}
