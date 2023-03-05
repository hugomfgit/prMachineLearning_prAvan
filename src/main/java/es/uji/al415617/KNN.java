package es.uji.al415617;

import java.util.List;

public class KNN {

    TableWithLabels tablaAlmacenada;

    public void train(TableWithLabels data){ //Almacena la Tabla Con Etiquetas con la que queremos entrenar la IA.
        tablaAlmacenada = data;
    }

    public Integer estimate(List<Double> data){ //Estima, a partir de una tabla dada con flores y sus respectivas clases, la clase que le correspondería a una nueva flor.
        double distMin=-1;
        int numClaseMin=-1;

        for(int indiceRow=0; indiceRow<tablaAlmacenada.filas.size(); indiceRow++){
            RowWithLabel flor = tablaAlmacenada.getRowAt(indiceRow);
            double distEuclidea=distanciaEuclidea(flor, data);
            if(distMin==-1){
                distMin=distEuclidea;
                numClaseMin=flor.getNumberClass();
            } else if(distEuclidea<=distMin){
                distMin=distEuclidea;
                numClaseMin=flor.getNumberClass();
            }
        }
        return numClaseMin;
    }

    public double distanciaEuclidea(RowWithLabel florDeLaTabla, List<Double> florNueva){ //Calcula la Distancia Euclidiana entre dos flores.
        double difColumna;
        double sumatorio=0;
        for(int indice=0; indice<florDeLaTabla.data.size(); indice++){
            difColumna=florDeLaTabla.data.get(indice)-florNueva.get(indice);
            difColumna=Math.pow(difColumna, 2);
            sumatorio+=difColumna;
        }
        double distEuclidea = Math.sqrt(sumatorio);
        return distEuclidea;
    }

}
