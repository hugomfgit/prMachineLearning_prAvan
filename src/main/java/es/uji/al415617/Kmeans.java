package es.uji.al415617;

import java.util.*;

// implements Algoritm<Integer,List>doubld> {
public class Kmeans implements Algorithm<Table, Integer, List<Double>> {

    int numClusters;
    int numIterations;
    long seed;

    Map<Integer, List<Row>> clusters;
    List<Row> centroides;

    public Kmeans(int numClusters, int numIterations, long seed){
        this.numClusters=numClusters;
        this.numIterations=numIterations;
        this.seed=seed;
        centroides = new ArrayList<>();
    }

    @Override
    public void train(Table datos) throws ExceptionMoreGroupsThanData {

        if(this.numClusters>datos.filas.size())
            throw new ExceptionMoreGroupsThanData();

        Random random = new Random(this.seed);
        this.centroides = createCentroides(datos, random);
        this.clusters = createClusters(datos, centroides);
    }

    @Override
    public Integer estimate(List<Double> dato){
        double distMin =  new Estadistica().distanciaEuclidea((RowWithLabel) centroides.get(0), dato);
        int indiceCentroide = 0;
        for (int indiceCen = 0; indiceCen < centroides.size(); indiceCen++) {
            double distEuclidea = new Estadistica().distanciaEuclidea((RowWithLabel) centroides.get(indiceCen), dato);
            if (distEuclidea <= distMin) {
                distMin = distEuclidea;
                indiceCentroide = indiceCen;
            }
        }
        return indiceCentroide;
    }

    public List<Row> createCentroides(Table datos, Random random){
        List<Row> centroides = new ArrayList<>();
        Set<Integer> indicesRandomizados = new HashSet<>();
        for(int contador=0; contador<3; contador++){
            int indiceRandomizado = random.nextInt(datos.filas.size());
            indicesRandomizados.add(indiceRandomizado);
            while (indicesRandomizados.contains(indiceRandomizado))
                indiceRandomizado = random.nextInt(datos.filas.size());

            Row centroide = datos.getRowAt(indiceRandomizado);
            centroides.add(centroide);
        }
        return centroides;
    }

    public Map<Integer, List<Row>> createClusters(Table datos, List<Row> centroides){
        clusters=new HashMap<>();
        double distMin =  new Estadistica().distanciaEuclidea((RowWithLabel) centroides.get(0), datos.filas.get(0).data);
        int indiceCentroide = 0;
        for(int indice=0; indice<numClusters; indice++)
            clusters.put(indice, new ArrayList<>());
        for (int iter = 0 ; iter < numIterations ; iter++) {
            for (int indiceRow = 0; indiceRow < datos.filas.size(); indiceRow++) {
                for (int indiceCen = 0; indiceCen < centroides.size(); indiceCen++) {
                    double distEuclidea = new Estadistica().distanciaEuclidea((RowWithLabel) centroides.get(indiceCen), datos.filas.get(indiceRow).data);
                    if (distEuclidea <= distMin) {
                        distMin = distEuclidea;
                        indiceCentroide = indiceCen;
                    }
                }
                clusters.get(indiceCentroide).add(datos.getRowAt(indiceRow));
            }
            for(int indice=0; indice<numClusters; indice++) //Asignamos los centros geométricos
                asignCentroide(createCentroGeometrico(clusters.get(indice)), indice);
        }
        return clusters;
    }


    public List<Double> createCentroGeometrico (List<Row> cluster){
        double xCentro=0;
        double yCentro=0;
        double zCentro=0;
        for(Row elementoDeGrupo:cluster){
            List<Double> datosElemento = elementoDeGrupo.data;
            xCentro+=datosElemento.get(0);
            yCentro+=datosElemento.get(1);
            zCentro+=datosElemento.get(2);
        }
        List<Double> centroGeometrico = new ArrayList<>();
        centroGeometrico.add(xCentro/cluster.size());
        centroGeometrico.add(yCentro/cluster.size());
        centroGeometrico.add(zCentro/cluster.size());

        return centroGeometrico;
    }


    public void asignCentroide (List<Double> centroGeometrico, int indiceCentroide){
        centroides.get(indiceCentroide).data=centroGeometrico;
    }

}
