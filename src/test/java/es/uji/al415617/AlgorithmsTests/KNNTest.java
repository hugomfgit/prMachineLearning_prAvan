package es.uji.al415617.AlgorithmsTests;

import es.uji.al415617.Algorithms.KNN;
import es.uji.al415617.Composition.Reading.CSV;
import es.uji.al415617.Composition.Rows.RowWithLabel;
import es.uji.al415617.Composition.Tables.TableWithLabels;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KNNTest {

    CSV csv_fichero=new CSV();
    KNN knnCsv = new KNN();
    TableWithLabels tabla = csv_fichero.readTableWithLabels("src/files/iris.csv");
    List<Double> fila5 = new ArrayList<>();
    RowWithLabel flor = tabla.getRowAt(0);

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void KNNClaseCorrect() {
        knnCsv.train(tabla);
        assertEquals(0,knnCsv.estimate(tabla.filas.get(0).data));
        System.out.println("Prueba numero filas Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void distEuclTest() {
        knnCsv.train(tabla);

        fila5.add(5.0);
        fila5.add(3.6);
        fila5.add(1.4);
        fila5.add(0.2);

        assertEquals(0,knnCsv.distanciaEuclidea(flor,fila5), 1);
        System.out.println("Prueba numero filas Table pasada.\n");
    }

}