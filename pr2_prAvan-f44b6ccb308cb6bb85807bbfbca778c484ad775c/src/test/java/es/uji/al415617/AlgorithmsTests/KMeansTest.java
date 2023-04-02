package es.uji.al415617.AlgorithmsTests;

import es.uji.al415617.Algorithms.KMeans;
import es.uji.al415617.Composition.Reading.CSV;
import es.uji.al415617.Composition.Rows.Row;
import es.uji.al415617.Composition.Tables.Table;
import es.uji.al415617.Exceptions.ExceptionMoreGroupsThanData;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KMeansTest {


    CSV constructorTablas = new CSV();

    KMeans estimadorClustersCuadrantes1y3 = new KMeans(2, 20, 4);

    KMeans estimadorMasClustersQueDatos = new KMeans(30, 10, 4);

    //Usaremos puntos del primer cuadrante (+,+) y del tercer cuadrante (-,-). Gracias a esta semilla, tercer cuadrante ---> 1.

    Table tablaCuadrantes1y3;

    //Métodos aportados por el profesorado:
    public void saveTableConPredicciones(Table t, String filename, KMeans miEstimador) throws IOException {
        try {
            FileWriter fw = new FileWriter(filename);
            for (int i=0; i<t.getNumRows(); i++)
            {
                Row row = t.getRowAt(i);
                List<Double> datos = row.getData();
                datos.add(Double.valueOf(miEstimador.estimate(datos)));
                int j=0;
                for (; j<datos.size()-1; j++)
                {
                    fw.write(datos.get(j).toString());
                    fw.write(",");
                }
                fw.write(datos.get(j).toString());
                fw.write("\n");
            }
            fw.close();
        } catch (IOException e) {
            throw e;
        }
    }

    //Constructor.
    public KMeansTest() throws ExceptionMoreGroupsThanData {
        tablaCuadrantes1y3 = constructorTablas.readTable("src" + File.separator + "Files" + File.separator + "DarosParaKmeansTest.csv");
        estimadorClustersCuadrantes1y3.train(tablaCuadrantes1y3);
        try {
            saveTableConPredicciones(tablaCuadrantes1y3, "src" + File.separator + "Files" + File.separator + "DarosParaKmeansTestConPredicciones.csv", estimadorClustersCuadrantes1y3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test //Comprobamos que lanza la excepción creada correctamente.
    void ExceptionMoreGroupsThanDataTest() throws ExceptionMoreGroupsThanData {
        assertThrows(ExceptionMoreGroupsThanData.class, () -> estimadorMasClustersQueDatos.train(tablaCuadrantes1y3));
    }

    @Test //Comprobamos que un punto del primer cuadrante (+,+) se asocia correctamente a un cluster de este cuadrante.
    void estimateEnCuadrante1() {
        List<Double> puntoDelCuadrante1 = new ArrayList();
        puntoDelCuadrante1.add(8.0);
        puntoDelCuadrante1.add(12.0);
        assertEquals(0, estimadorClustersCuadrantes1y3.estimate(puntoDelCuadrante1));
    }

    @Test //Comprobamos que un punto del tercer cuadrante (-,-) se asocia correctamente a un cluster de este cuadrante.
    void estimateEnCuadrante3() {
        List<Double> puntoDelCuadrante3 = new ArrayList();
        puntoDelCuadrante3.add(-8.0);
        puntoDelCuadrante3.add(-12.0);
        assertEquals(1, estimadorClustersCuadrantes1y3.estimate(puntoDelCuadrante3));
    }

    @Test
    void estimateEnClustersDiferentes() {
        List<Double> puntoDelCuadrante4 = new ArrayList();
        puntoDelCuadrante4.add(-15.0);
        puntoDelCuadrante4.add(8.0);
        List<Double> puntoDelCuadrante1 = new ArrayList();
        puntoDelCuadrante1.add(8.0);
        puntoDelCuadrante1.add(15.0);
        assertNotEquals(estimadorClustersCuadrantes1y3.estimate(puntoDelCuadrante1), estimadorClustersCuadrantes1y3.estimate(puntoDelCuadrante4));

    }
}
