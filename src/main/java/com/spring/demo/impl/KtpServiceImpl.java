package com.spring.demo.impl;

import com.spring.demo.dto.KtpDto;
import com.spring.demo.entity.Ktp;
import com.spring.demo.mapper.KtpMapper;
import com.spring.demo.repository.KtpRepository;
import com.spring.demo.service.KtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class KtpServiceImpl implements KtpService {

    @Autowired
    private KtpRepository repository;

    @Override
    public List<KtpDto> getAllKtp() {
        return repository.findAll().stream()
                .map(KtpMapper::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public KtpDto getKtpById(Integer id) {
        Ktp ktp = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data tidak ditemukan"));
        return KtpMapper.mapToDto(ktp);
    }

    @Override
    public KtpDto createKtp(KtpDto ktpDto) {
        if (repository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new IllegalArgumentException("Nomor KTP sudah terdaftar!");
        }
        Ktp ktp = KtpMapper.mapToEntity(ktpDto);
        return KtpMapper.mapToDto(repository.save(ktp));
    }

    @Override
    public KtpDto updateKtp(Integer id, KtpDto ktpDto) {
        Ktp existingKtp = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data tidak ditemukan"));

        if (!existingKtp.getNomorKtp().equals(ktpDto.getNomorKtp()) && repository.existsByNomorKtp(ktpDto.getNomorKtp())) {
            throw new IllegalArgumentException("Nomor KTP sudah terdaftar!");
        }

        existingKtp.setNomorKtp(ktpDto.getNomorKtp());
        existingKtp.setNamaLengkap(ktpDto.getNamaLengkap());
        existingKtp.setAlamat(ktpDto.getAlamat());
        existingKtp.setTanggalLahir(ktpDto.getTanggalLahir());
        existingKtp.setJenisKelamin(ktpDto.getJenisKelamin());

        return KtpMapper.mapToDto(repository.save(existingKtp));
    }

    @Override
    public void deleteKtp(Integer id) {
        repository.deleteById(id);
    }
}