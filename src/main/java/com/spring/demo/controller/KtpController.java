package com.spring.demo.controller;

import com.spring.demo.dto.KtpDto;
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
    public List<KtpDto> getAll() {
        return service.getAllKtp();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(service.getKtpById(id));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody KtpDto ktpDto) {
        try {
            return ResponseEntity.ok(service.createKtp(ktpDto));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @Valid @RequestBody KtpDto ktpDto) {
        try {
            return ResponseEntity.ok(service.updateKtp(id, ktpDto));
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