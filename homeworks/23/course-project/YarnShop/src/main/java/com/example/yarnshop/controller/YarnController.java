package com.example.yarnshop.controller;

import com.example.yarnshop.entity.Yarn;
import com.example.yarnshop.services.YarnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/yarns")
public class YarnController {
    private final YarnService yarnService;

    @Autowired
    public YarnController(YarnService yarnService) {
        this.yarnService = yarnService;
    }

    @GetMapping
    public List<Yarn> getAllYarns() {
        return yarnService.getAllYarns();
    }

    @GetMapping("/{id}")
    public Yarn getYarnById(@PathVariable Long id) {
        return yarnService.getYarnById(id);
    }

    @PostMapping
    public Yarn createYarn(@RequestBody Yarn yarn) {
        return yarnService.createYarn(yarn);
    }

    @PutMapping("/{id}")
    public Yarn updateYarn(@PathVariable Long id, @RequestBody Yarn yarn) {
        return yarnService.updateYarn(id, yarn);
    }

    @DeleteMapping("/{id}")
    public void deleteYarn(@PathVariable Long id) {
        yarnService.deleteYarn(id);
    }
}
