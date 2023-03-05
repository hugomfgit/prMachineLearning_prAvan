package es.uji.al415617;

import java.util.List;
import java.util.Map;

public class TableWithLabels extends Table{

    Map<String, Integer> labelsToIndex;

    public TableWithLabels(List<String> listaDeHeaders) { //Constructor. Crea una Tabla con Etiquetas con sus cabeceras o headers.
        super(listaDeHeaders);
    }

    public void addRowWithLabels(RowWithLabel fila){ //Añade una columna con etiquetas o RowWithLabel a la tabla.
        filas.add(fila);
    }

    public RowWithLabel getRowAt(int indiceDeRow){ //Devuelve la columna con etiquetas o RowWithLabel de la tabla dado su índice en la misma.
        return (RowWithLabel) this.filas.get(indiceDeRow);
    }

}
