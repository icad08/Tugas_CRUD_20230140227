package com.spring.demo.repository;

import com.spring.demo.entity.Ktp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KtpRepository extends JpaRepository<Ktp, Integer> {
    boolean existsByNomorKtp(String nomorKtp);
}