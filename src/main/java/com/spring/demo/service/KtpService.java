package com.spring.demo.service;

import com.spring.demo.dto.KtpDto;
import java.util.List;

public interface KtpService {
    List<KtpDto> getAllKtp();
    KtpDto getKtpById(Integer id);
    KtpDto createKtp(KtpDto ktpDto);
    KtpDto updateKtp(Integer id, KtpDto ktpDto);
    void deleteKtp(Integer id);
}