package com.example.restaurant.controller;

import com.example.restaurant.controller.resources.ClientResource;
import com.example.restaurant.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ClientResource clientResource) {
        ClientResource createdClientResource = clientService.save(clientResource);

        return ResponseEntity.created(
                UriComponentsBuilder.fromPath("/clients/{id}")
                        .buildAndExpand(createdClientResource.getId())
                        .toUri()
        ).body(createdClientResource);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ClientResource clientResource) {
        ClientResource updatedClientResource = clientService.update(id, clientResource);

        return ResponseEntity.ok(updatedClientResource);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
