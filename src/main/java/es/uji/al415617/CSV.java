package es.uji.al415617;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {
    public Table readTable(String ficheroDatos) throws FileNotFoundException{
        Scanner sc = new Scanner(ficheroDatos); //Creamos un scanner para leer el fichero dado.
        String[] vectorLinea = sc.nextLine().split(",");
        List<String> listaDeHeaders = conversorAListaAux(vectorLinea); //Creamos una lista con los headers.
        Table tablaSinIndices = new Table(listaDeHeaders); //Creamos una tabla que los incluya.
        while(sc.hasNext()){
            vectorLinea = sc.nextLine().split(",");
            List<Double> listaDeLaFila = conversorAListaAux(vectorLinea);
            Row fila = new Row(listaDeLaFila);
            tablaSinIndices.addRow(fila);
        } //Hemos creado y añadido todas las rows a la tabla.
        return tablaSinIndices; //Devolvemos la tabla.
    }
    public <T> List<T> conversorAListaAux (String[] vectorLinea){ //Método auxiliar. Convierte un vector de String en una Lista con el tipo que indiquemos.
        List<T> listaLinea= new ArrayList<>();

        for(int indiceH=0; indiceH<vectorLinea.length-1; indiceH++)
            listaLinea.add((T) vectorLinea[indiceH]);
        return listaLinea;
    }


    public TableWithLabels readTableWithLabels(String ficheroDatos) throws FileNotFoundException {
        Scanner sc = new Scanner(ficheroDatos); //Creamos un scanner para leer el fichero dado.
        String[] headersDivididos = sc.nextLine().split(",");
        List<String> listaDeHeaders = conversorAListaAux(headersDivididos);
        listaDeHeaders.add(headersDivididos[headersDivididos.length]);
        TableWithLabels tablaConIndices = new TableWithLabels(listaDeHeaders); //Creamos una tabla con los headers como primera columna.

        while(sc.hasNext()){
            String[] filaDividida = sc.nextLine().split(",");
            List<Double> listaDeLaFila = conversorAListaAux(filaDividida);
            String claseDeFlor = filaDividida[filaDividida.length];
            int indiceNumClase = elegirNumClaseAux(tablaConIndices, claseDeFlor);
            RowWithLabel fila = new RowWithLabel(listaDeLaFila, indiceNumClase);
            tablaConIndices.addRowWithLabels(fila);
        } //Hemos añadido todas las columnas con número de clase a la tabla

        return tablaConIndices;
    }

    public int elegirNumClaseAux(TableWithLabels tablaConIndices, String claseDeFlor){ //Método auxiliar. Elige el número de clase que le corresponde a una flor dada y, si este no existía, lo introduce en el mapa de la Tabla Con Etiquetas dada.
        int indiceNumClase = tablaConIndices.labelsToIndex.keySet().size();
        if(tablaConIndices.labelsToIndex.containsKey(claseDeFlor)){
            indiceNumClase=tablaConIndices.labelsToIndex.get(claseDeFlor);
        } else {
            tablaConIndices.labelsToIndex.put(claseDeFlor,indiceNumClase);
        }
        return indiceNumClase;
    }

}
