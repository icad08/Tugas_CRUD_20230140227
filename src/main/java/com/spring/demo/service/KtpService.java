package com.spring.demo.service;

import com.spring.demo.model.Ktp;
import com.spring.demo.repository.KtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KtpService {

    @Autowired
    private KtpRepository repository;

    public List<Ktp> getAllKtp() {
        return repository.findAll();
    }

    public Optional<Ktp> getKtpById(Integer id) {
        return repository.findById(id);
    }

    public Ktp createKtp(Ktp ktp) {
        if (repository.existsByNomorKtp(ktp.getNomorKtp())) {
            throw new IllegalArgumentException("Nomor KTP sudah terdaftar!");
        }
        return repository.save(ktp);
    }

    public Ktp updateKtp(Integer id, Ktp ktpDetails) {
        Ktp ktp = repository.findById(id).orElseThrow(() -> new IllegalArgumentException("Data KTP tidak ditemukan"));

        if(!ktp.getNomorKtp().equals(ktpDetails.getNomorKtp()) && repository.existsByNomorKtp(ktpDetails.getNomorKtp())) {
            throw new IllegalArgumentException("Nomor KTP sudah terdaftar!");
        }

        ktp.setNomorKtp(ktpDetails.getNomorKtp());
        ktp.setNamaLengkap(ktpDetails.getNamaLengkap());
        ktp.setAlamat(ktpDetails.getAlamat());
        ktp.setTanggalLahir(ktpDetails.getTanggalLahir());
        ktp.setJenisKelamin(ktpDetails.getJenisKelamin());

        return repository.save(ktp);
    }

    public void deleteKtp(Integer id) {
        repository.deleteById(id);
    }
}