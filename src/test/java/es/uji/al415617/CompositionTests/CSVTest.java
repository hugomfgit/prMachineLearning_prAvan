package es.uji.al415617.CompositionTests;

import es.uji.al415617.Composition.Reading.CSV;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CSVTest {

    CSV csv_fichero=new CSV();

    @org.junit.jupiter.api.Test
    void readFilasTable() {
        assertEquals(150,csv_fichero.readTable("src/files/iris.csv").getNumRows());
        System.out.println("Prueba número filas de Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void readColTable() {
        assertEquals(4,csv_fichero.readTable("src/files/iris.csv").getRowAt(0).data.size());
        System.out.println("Prueba número columnas de Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void readLabelTable() {
        List<String> label = new ArrayList<>();
        label.add("sepal length");
        label.add("sepal width");
        label.add("petal length");
        label.add("petal width");
        assertEquals(label,csv_fichero.readTable("src/files/iris.csv").headers);
        System.out.println("Prueba etiquetas de Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void TableNumFila() {
        List<Double> fila5 = new ArrayList<>();
        fila5.add(5.0);
        fila5.add(3.6);
        fila5.add(1.4);
        fila5.add(0.2);

        assertEquals(fila5,csv_fichero.readTable("src/files/iris.csv").getRowAt(4).data);
        System.out.println("Prueba numero de Row en Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void readFilasTableWithLabels() {
        assertEquals(150,csv_fichero.readTableWithLabels("src/files/iris.csv").getNumRows());
        System.out.println("Primera prueba de readTableWithLabels pasada.\n");
    }


    @org.junit.jupiter.api.Test
    void readColTableWithLabels() {
        assertEquals(4,csv_fichero.readTable("src/files/iris.csv").getRowAt(0).data.size());
        System.out.println("Prueba número columnas Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void readLabelTableWithLabels() {
        List<String> label = new ArrayList<>();
        label.add("sepal length");
        label.add("sepal width");
        label.add("petal length");
        label.add("petal width");
        assertEquals(label,csv_fichero.readTable("src/files/iris.csv").headers);
        System.out.println("Prueba etiquetas Table pasada.\n");
    }

    @org.junit.jupiter.api.Test
    void TableNumFilaWithLabels() {
        List<Double> fila5 = new ArrayList<>();
        fila5.add(5.0);
        fila5.add(3.6);
        fila5.add(1.4);
        fila5.add(0.2);

        assertEquals(fila5,csv_fichero.readTable("src/files/iris.csv").getRowAt(4).data);
        System.out.println("Prueba numero de Row en Table pasada.\n");
    }

}