package com.kuliah;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class StudentGradeTest {

    private StudentGrade sg;

    @BeforeEach
    void setUp() {
        sg = new StudentGrade();
    }

    // ---------- calculateGrade ----------
    @ParameterizedTest(name = "score {0} -> grade {1}")
    @CsvSource({
            "100, A", "90, A", "80, A",   // path A (+ batas bawah 80)
            "79, B",  "75, B", "70, B",   // path B
            "69, C",  "65, C", "60, C",   // path C
            "59, D",  "55, D", "50, D",   // path D
            "49, E",  "25, E", "0, E"     // path E
    })
    @DisplayName("calculateGrade mengembalikan grade yang benar")
    void calculateGrade_valid(int score, String expected) {
        assertEquals(expected, sg.calculateGrade(score));
    }

    @Test
    @DisplayName("calculateGrade: score negatif -> exception")
    void calculateGrade_negatif() {
        IllegalArgumentException ex =
                assertThrows(IllegalArgumentException.class, () -> sg.calculateGrade(-1));
        assertEquals("Score must be between 0 and 100", ex.getMessage());
    }

    @Test
    @DisplayName("calculateGrade: score > 100 -> exception")
    void calculateGrade_lebih100() {
        assertThrows(IllegalArgumentException.class, () -> sg.calculateGrade(101));
    }

    // ---------- isPassed ----------
    @Test
    @DisplayName("isPassed: score 60 (batas) -> lulus")
    void isPassed_batasLulus() {
        assertTrue(sg.isPassed(60));
    }

    @Test
    @DisplayName("isPassed: score 100 -> lulus")
    void isPassed_maks() {
        assertTrue(sg.isPassed(100));
    }

    @Test
    @DisplayName("isPassed: score 59 -> tidak lulus")
    void isPassed_dibawahBatas() {
        assertFalse(sg.isPassed(59));
    }

    @Test
    @DisplayName("isPassed: score 0 -> tidak lulus")
    void isPassed_nol() {
        assertFalse(sg.isPassed(0));
    }

    @Test
    @DisplayName("isPassed: score negatif -> exception")
    void isPassed_negatif() {
        assertThrows(IllegalArgumentException.class, () -> sg.isPassed(-5));
    }

    @Test
    @DisplayName("isPassed: score > 100 -> exception")
    void isPassed_lebih100() {
        assertThrows(IllegalArgumentException.class, () -> sg.isPassed(150));
    }
}
