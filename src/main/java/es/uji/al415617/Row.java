package es.uji.al415617;

import java.util.ArrayList;
import java.util.List;

public class Row {

    List<Double> data;

    public Row(){
        data = new ArrayList<>();
    }
    public Row(List<Double> listaDeLaFila){ //Constructor. Crea una Row a partir de una lista de elementos Double con el contenido de la columna que queremos crear.
        data = listaDeLaFila;
    }

    public List<Double> getData(){ //Devuelve el contenido (Lista con campos Double) correspondiente a la columna o Row.
        return data;
    }

}
