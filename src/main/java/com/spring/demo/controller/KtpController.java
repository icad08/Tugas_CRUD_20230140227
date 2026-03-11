package com.spring.demo.controller;

import com.spring.demo.model.Ktp;
import com.spring.demo.service.KtpService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ktp")
public class KtpController {

    @Autowired
    private KtpService service;

    @GetMapping
    public List<Ktp> getAll() {
        return service.getAllKtp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        return service.getKtpById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Ktp ktp) {
        try {
            return ResponseEntity.ok(service.createKtp(ktp));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody Ktp ktp) {
        try {
            return ResponseEntity.ok(service.updateKtp(id, ktp));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        service.deleteKtp(id);
        return ResponseEntity.ok("Data berhasil dihapus");
    }
}