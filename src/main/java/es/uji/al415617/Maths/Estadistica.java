package es.uji.al415617.Maths;

import es.uji.al415617.Composition.Rows.RowWithLabel;

import java.util.List;

public class Estadistica {
    public Estadistica(){
        super();
    }
    public double distanciaEuclidea(RowWithLabel florDeLaTabla, List<Double> florNueva){ //Calcula la Distancia Euclidiana entre dos flores.
        double difColumna;
        double sumatorio=0;
        double ElmFlorTabl;
        double ElmFlorNew;

        for(int indice=0; indice<florDeLaTabla.data.size(); indice++){
            ElmFlorNew = florNueva.get(indice);
            ElmFlorTabl = florDeLaTabla.data.get(indice);

            difColumna = ElmFlorTabl - ElmFlorNew;
            difColumna = Math.pow(difColumna, 2);
            sumatorio += difColumna;
        }
        double distEuclidea = Math.sqrt(sumatorio);
        return distEuclidea;
    }
}
