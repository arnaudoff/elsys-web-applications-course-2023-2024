package com.example.yarnshop.services.implementation;

import com.example.yarnshop.entity.Pattern;
import com.example.yarnshop.repository.PatternRepository;
import com.example.yarnshop.services.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatternServiceImplementation implements PatternService {
    private final PatternRepository patternRepository;

    @Autowired
    public PatternServiceImplementation(PatternRepository patternRepository) {
        this.patternRepository = patternRepository;
    }

    @Override
    public List<Pattern> getAllPatterns() {
        return patternRepository.findAll();
    }

    @Override
    public Pattern getPatternById(Long id) {
        Optional<Pattern> optionalPattern = patternRepository.findById(id);
        return optionalPattern.orElse(null);
    }

    @Override
    public Pattern createPattern(Pattern pattern) {
        return patternRepository.save(pattern);
    }

    @Override
    public Pattern updatePattern(Long id, Pattern pattern) {
        Optional<Pattern> optionalPattern = patternRepository.findById(id);
        if (optionalPattern.isPresent()) {
            Pattern existingPattern = optionalPattern.get();
            existingPattern.setName(pattern.getName());
            existingPattern.setDescription(pattern.getDescription());
            return patternRepository.save(existingPattern);
        }
        return null;
    }

    @Override
    public void deletePattern(Long id) {
        patternRepository.deleteById(id);
    }
}
