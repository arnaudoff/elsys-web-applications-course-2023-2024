package com.example.demo.services;

import com.example.demo.controlers.resources.FineRes;
import com.example.demo.entities.Fine;
import com.example.demo.entities.User;
import java.util.List;

public interface FineService {
    List<FineRes> findAll();
    FineRes findById(Long id);
    FineRes save(FineRes fine);
    FineRes update(FineRes fine, Long id);
    void deleteById(Long id);

    void updateAll(List<Fine> fines, List<Fine> fines1);
}
