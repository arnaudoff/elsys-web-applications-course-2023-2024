package com.example.yarnshop.resources;

import com.example.yarnshop.entity.Pattern;
import com.example.yarnshop.services.PatternService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patterns")
public class PatternResource {
    private final PatternService patternService;

    @Autowired
    public PatternResource(PatternService patternService) {
        this.patternService = patternService;
    }

    @GetMapping
    public List<Pattern> getAllPatterns() {
        return patternService.getAllPatterns();
    }

    @GetMapping("/{id}")
    public Pattern getPatternById(@PathVariable Long id) {
        return patternService.getPatternById(id);
    }

    @PostMapping
    public Pattern createPattern(@RequestBody Pattern pattern) {
        return patternService.createPattern(pattern);
    }

    @PutMapping("/{id}")
    public Pattern updatePattern(@PathVariable Long id, @RequestBody Pattern pattern) {
        return patternService.updatePattern(id, pattern);
    }

    @DeleteMapping("/{id}")
    public void deletePattern(@PathVariable Long id) {
        patternService.deletePattern(id);
    }
}
