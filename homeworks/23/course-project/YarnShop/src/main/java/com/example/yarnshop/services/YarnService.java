package com.example.yarnshop.services;

import com.example.yarnshop.entity.Yarn;

import java.util.List;

public interface YarnService {
    List<Yarn> getAllYarns();
    Yarn getYarnById(Long id);
    Yarn createYarn(Yarn yarn);
    Yarn updateYarn(Long id, Yarn yarn);
    void deleteYarn(Long id);
}
