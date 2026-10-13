package com.kuliah;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegistrationValidatorTest {

    private RegistrationValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RegistrationValidator();
    }

    @Test
    @DisplayName("TC1 - Path 1: nama kosong")
    void tc1_namaKosong() {
        assertEquals("Nama harus diisi",
                validator.validasiPendaftaran("", 20, "a@mail.com", true));
    }

    @Test
    @DisplayName("TC2 - Path 2: umur < 17")
    void tc2_umurKurang() {
        assertEquals("Umur minimal 17 tahun",
                validator.validasiPendaftaran("Budi", 16, "a@mail.com", true));
    }

    @Test
    @DisplayName("TC3 - Path 3: email tidak mengandung @")
    void tc3_emailTanpaAt() {
        assertEquals("Email tidak valid",
                validator.validasiPendaftaran("Budi", 20, "budimail.com", true));
    }

    @Test
    @DisplayName("TC4 - Path 4: tidak setuju syarat")
    void tc4_tidakSetuju() {
        assertEquals("Harus menyetujui syarat dan ketentuan",
                validator.validasiPendaftaran("Budi", 20, "a@mail.com", false));
    }

    @Test
    @DisplayName("TC5 - Path 5: semua valid")
    void tc5_berhasil() {
        assertEquals("Pendaftaran berhasil",
                validator.validasiPendaftaran("Budi", 20, "a@mail.com", true));
    }

    // Tambahan: kondisi null (cabang kedua pada P1 dan P3) + batas umur
    @Test
    @DisplayName("Tambahan - nama null")
    void namaNull() {
        assertEquals("Nama harus diisi",
                validator.validasiPendaftaran(null, 20, "a@mail.com", true));
    }

    @Test
    @DisplayName("Tambahan - email null")
    void emailNull() {
        assertEquals("Email tidak valid",
                validator.validasiPendaftaran("Budi", 20, null, true));
    }

    @Test
    @DisplayName("Tambahan - batas umur tepat 17 diterima")
    void umurBatas17() {
        assertEquals("Pendaftaran berhasil",
                validator.validasiPendaftaran("Budi", 17, "a@mail.com", true));
    }
}
