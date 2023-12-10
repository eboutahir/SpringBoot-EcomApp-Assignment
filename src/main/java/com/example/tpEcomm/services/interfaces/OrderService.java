package com.example.tpEcomm.services.interfaces;


import com.example.tpEcomm.models.Orderr;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface OrderService {
    List<Orderr> findAll();
    Orderr save(Orderr order);
    Optional<Orderr> findById(Long id);
    void Delete(Long id);
    Orderr update(Long id, Orderr order);
    void deleteById(Long id);
}
