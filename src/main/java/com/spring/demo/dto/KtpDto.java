package com.spring.demo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public class KtpDto {
    private Integer id;

    @NotBlank(message = "Nomor KTP tidak boleh kosong")
    @Pattern(regexp = "^[0-9]{16}$", message = "Nomor KTP wajib 16 digit angka")
    @JsonProperty("nomorKtp")
    private String nomorKtp;

    @NotBlank(message = "Nama lengkap tidak boleh kosong")
    @JsonProperty("namaLengkap")
    private String namaLengkap;

    @NotBlank(message = "Alamat tidak boleh kosong")
    @JsonProperty("alamat")
    private String alamat;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @JsonProperty("tanggalLahir")
    private LocalDate tanggalLahir;

    @JsonProperty("jenisKelamin")
    private String jenisKelamin;

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomorKtp() { return nomorKtp; }
    public void setNomorKtp(String nomorKtp) { this.nomorKtp = nomorKtp; }
    public String getNamaLengkap() { return namaLengkap; }
    public void setNamaLengkap(String namaLengkap) { this.namaLengkap = namaLengkap; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public LocalDate getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(LocalDate tanggalLahir) { this.tanggalLahir = tanggalLahir; }
    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }
}