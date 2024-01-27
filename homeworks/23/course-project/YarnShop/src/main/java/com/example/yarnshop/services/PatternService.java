package com.example.yarnshop.services;

import com.example.yarnshop.entity.Pattern;

import java.util.List;

public interface PatternService {
    List<Pattern> getAllPatterns();
    Pattern getPatternById(Long id);
    Pattern createPattern(Pattern pattern);
    Pattern updatePattern(Long id, Pattern pattern);
    void deletePattern(Long id);
}
