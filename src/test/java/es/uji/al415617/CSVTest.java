package es.uji.al415617;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void readTable() {
        assertEquals(150, new CSV().readTable("C:\\Users\\Work\\Documents\\pr1_prAvan\\src\\files\\iris.csv").headers.size());
    }

    @org.junit.jupiter.api.Test
    void readTableWithLabels() {
    }
}