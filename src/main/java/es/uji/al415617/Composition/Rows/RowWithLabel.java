package es.uji.al415617.Composition.Rows;

import java.util.List;

public class RowWithLabel extends Row{

    int numberClass;

    public RowWithLabel(){
        super();
    }

    public RowWithLabel(List<Double> listaDeLaFila, int indiceNumClase) { //Constructor. Crea una RowWithLabel a partir de una lista de elementos Double con el contenido de la columna que queremos crear y su número de clase.
        super(listaDeLaFila);
        numberClass=indiceNumClase;
    }

    public int getNumberClass(){ //Devuelve el número de clase correspondiente a la flor (columna).
        return numberClass;
    }
}
