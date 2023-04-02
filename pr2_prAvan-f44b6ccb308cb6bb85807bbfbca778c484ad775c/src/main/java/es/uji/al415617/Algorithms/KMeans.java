package es.uji.al415617.Algorithms;

import es.uji.al415617.Composition.Rows.Row;
import es.uji.al415617.Composition.Rows.RowWithLabel;
import es.uji.al415617.Composition.Tables.Table;
import es.uji.al415617.Exceptions.ExceptionMoreGroupsThanData;
import es.uji.al415617.Interfaces.Algorithm;
import es.uji.al415617.Maths.Estadistica;

import java.util.*;

// implements Algoritm<Integer,List>doubld> {
public class KMeans implements Algorithm<Table, Integer, List<Double>> {

    int numClusters;
    int numIterations;
    long seed;

    Map<Integer, List<Row>> clusters;
    List<Row> centroides;

    public KMeans(int numClusters, int numIterations, long seed){
        this.numClusters=numClusters;
        this.numIterations=numIterations;
        this.seed=seed;
        centroides = new ArrayList<>();
    }

    @Override
    public void train(Table datos) throws ExceptionMoreGroupsThanData {

        if(this.numClusters>datos.getNumRows()) //Lanza una excepción cuando se piden más clusters que el número de datos con los que contamos
            throw new ExceptionMoreGroupsThanData();

        Random random = new Random(this.seed);
        this.centroides = createCentroides(datos, random);
        this.clusters = createClusters(datos, centroides);
    }

    @Override
    public Integer estimate(List<Double> dato){
        double distMin =  new Estadistica().distanciaEuclidea(centroides.get(0).data, dato);
        int indiceCentroide = 0;
        for (int indiceCen = 0; indiceCen < centroides.size(); indiceCen++) {
            double distEuclidea = new Estadistica().distanciaEuclidea(centroides.get(indiceCen).data, dato);
            if (distEuclidea <= distMin) {
                distMin = distEuclidea;
                indiceCentroide = indiceCen;
            }
        }
        return indiceCentroide;
    }

    private List<Row> createCentroides(Table datos, Random random){
        List<Row> centroides = new ArrayList<>();
        Set<Integer> indicesRandomizados = new HashSet<>();
        for(int contador=0; contador<numClusters; contador++){
            int indiceRandomizado = random.nextInt(datos.getNumRows());
            indicesRandomizados.add(indiceRandomizado);
            while (indicesRandomizados.contains(indiceRandomizado))
                indiceRandomizado = random.nextInt(datos.getNumRows());

            Row centroide = datos.getRowAt(indiceRandomizado);
            centroides.add(centroide);
        }
        return centroides;
    }

    private Map<Integer, List<Row>> createClusters(Table datos, List<Row> centroides){
        clusters=new HashMap<>();
        double distMin =  new Estadistica().distanciaEuclidea(centroides.get(0).data, datos.getRowAt(0).data);
        int indiceCentroide = 0;
        for(int indice=0; indice<numClusters; indice++)
            clusters.put(indice, new ArrayList<>());
        for (int iter = 0 ; iter < numIterations ; iter++) {
            for (int indiceRow = 0; indiceRow < datos.getNumRows(); indiceRow++) {
                for (int indiceCen = 0; indiceCen < centroides.size(); indiceCen++) {
                    double distEuclidea = new Estadistica().distanciaEuclidea(centroides.get(indiceCen).data, datos.getRowAt(indiceRow).data);
                    if (distEuclidea <= distMin) {
                        distMin = distEuclidea;
                        indiceCentroide = indiceCen;
                    }
                }
                clusters.get(indiceCentroide).add(datos.getRowAt(indiceRow));
                System.out.println("ROW: " + datos.getRowAt(indiceRow).data);
            }
            for(int indice=0; indice<numClusters; indice++) //Asignamos los centros geométricos
                asignCentroide(createCentroGeometrico(clusters.get(indice)), indice);
        }
        return clusters;
    }


    private List<Double> createCentroGeometrico (List<Row> cluster){
        int numRows = cluster.size();
        int numCoor = cluster.get(0).getData().size();

        List<Double> centroGeometrico = new ArrayList<>();
        for (int i = 0; i < numCoor; i++) {
            double suma = 0.0;
            for (Row elementoDeGrupo : cluster) {
                suma += elementoDeGrupo.getData().get(i);
            }
            centroGeometrico.add(suma/numRows);
        }
        return centroGeometrico;
    }


    private void asignCentroide (List<Double> centroGeometrico, int indiceCentroide){
        centroides.get(indiceCentroide).data=centroGeometrico;
    }

}
