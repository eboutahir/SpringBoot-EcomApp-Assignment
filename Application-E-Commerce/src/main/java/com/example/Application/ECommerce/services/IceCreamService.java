package com.example.Application.ECommerce.services;

import com.example.Application.ECommerce.models.IceCream;

import java.util.List;

public interface IceCreamService {
    IceCream getById(Long id);
    List<IceCream> findAll();
    IceCream save(IceCream iceCream);

}
