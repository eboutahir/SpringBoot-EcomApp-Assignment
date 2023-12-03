package Ecommerce.ecommerce.Services;

import Ecommerce.ecommerce.Models.Cart;
import Ecommerce.ecommerce.Repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImp implements CartService{
    @Autowired
    private ProductService productService;


    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addCategory ( Cart cart ) {
        return cartRepository.save (cart);
    }

    @Override
    public Optional<Cart> getById ( Integer id ) {
        return cartRepository.findById (id);
    }

    @Override
    public List<Cart> getAll ( ) {
        return cartRepository.findAll ();
    }
}
