package com.example.Application.ECommerce.services.Impl;

import com.example.Application.ECommerce.models.IceCream;
import com.example.Application.ECommerce.respositories.IcecreamRepository;
import com.example.Application.ECommerce.services.IceCreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IceCreamImplService implements IceCreamService {
    @Autowired
    public  IcecreamRepository icecreamRepository;







    @Override
    public IceCream getById(Long id) {
        return icecreamRepository.getById(id);
    }

    @Override
    public List<IceCream> findAll() {
        return icecreamRepository.findAll();
    }

    @Override
    public IceCream save(IceCream iceCream) {
        return icecreamRepository.save(iceCream);
    }
}
