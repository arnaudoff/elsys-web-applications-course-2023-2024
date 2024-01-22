package com.example.yarnshop.services.implementation;

import com.example.yarnshop.entity.Yarn;
import com.example.yarnshop.repository.YarnRepository;
import com.example.yarnshop.services.YarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class YarnServiceImplementation implements YarnService {
    private final YarnRepository yarnRepository;

    @Autowired
    public YarnServiceImplementation(YarnRepository yarnRepository) {
        this.yarnRepository = yarnRepository;
    }

    @Override
    public List<Yarn> getAllYarns() {
        return yarnRepository.findAll();
    }

    @Override
    public Yarn getYarnById(Long id) {
        Optional<Yarn> optionalYarn = yarnRepository.findById(id);
        return optionalYarn.orElse(null);
    }

    @Override
    public Yarn createYarn(Yarn yarn) {
        return yarnRepository.save(yarn);
    }

    @Override
    public Yarn updateYarn(Long id, Yarn yarn) {
        Optional<Yarn> optionalYarn = yarnRepository.findById(id);
        if (optionalYarn.isPresent()) {
            Yarn existingYarn = optionalYarn.get();
            existingYarn.setName(yarn.getName());
            existingYarn.setColor(yarn.getColor());
            return yarnRepository.save(existingYarn);
        }
        return null;
    }

    @Override
    public void deleteYarn(Long id) {
        yarnRepository.deleteById(id);
    }
}
