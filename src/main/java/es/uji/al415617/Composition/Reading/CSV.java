package es.uji.al415617.Composition.Reading;

import es.uji.al415617.Composition.Rows.Row;
import es.uji.al415617.Composition.Rows.RowWithLabel;
import es.uji.al415617.Composition.Tables.Table;
import es.uji.al415617.Composition.Tables.TableWithLabels;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {

    public CSV(){
        super();
    }

    public Table readTable(String ficheroDatos) {
        Scanner sc = null; //Creamos un scanner para leer el fichero dado.
        try {sc = new Scanner(new File(ficheroDatos));}
        catch (FileNotFoundException e) {e.printStackTrace();}

        String[] vectorLinea = sc.nextLine().split(",");
        List<String> listaDeHeaders = conversorAListaStringAux(vectorLinea); //Creamos una lista con los headers.
        Table tablaSinIndices = new Table(listaDeHeaders); //Creamos una tabla que los incluya.

        while(sc.hasNext()){
            vectorLinea = sc.nextLine().split(",");
            List<Double> listaDeLaFila = conversorAListaDoubleAux(vectorLinea);
            Row fila = new Row(listaDeLaFila);
            tablaSinIndices.addRow(fila);
        } //Hemos creado y añadido todas las rows a la tabla.

        sc.close();
        return tablaSinIndices; //Devolvemos la tabla.
    }

    private List<String> conversorAListaStringAux (String[] vectorLinea){ //Método auxiliar. Convierte un vector de String en una Lista con el tipo que indiquemos.
        List<String> listaLinea= new ArrayList<>();

        for(int indiceH=0; indiceH<vectorLinea.length-1; indiceH++)
            listaLinea.add(vectorLinea[indiceH]);
        return listaLinea;
    }

    private List<Double> conversorAListaDoubleAux (String[] vectorLinea){ //Método auxiliar. Convierte un vector de String en una Lista con el tipo que indiquemos.
        List<Double> listaLinea= new ArrayList<>();

        for(int indiceH=0; indiceH<vectorLinea.length-1; indiceH++)
            listaLinea.add(Double.parseDouble(vectorLinea[indiceH]));
        return listaLinea;
    }

    public TableWithLabels readTableWithLabels(String ficheroDatos){
        Scanner sc = null; //Creamos un scanner para leer el fichero dado.
        try {sc = new Scanner(new File(ficheroDatos));}
        catch (FileNotFoundException e) {e.printStackTrace();}

        String[] headersDivididos = sc.nextLine().split(",");
        List<String> listaDeHeaders = conversorAListaStringAux(headersDivididos);
        listaDeHeaders.add(headersDivididos[headersDivididos.length-1]);
        TableWithLabels tablaConIndices = new TableWithLabels(listaDeHeaders); //Creamos una tabla con los headers como primera columna.
        while(sc.hasNext()){
            String[] filaDividida = sc.nextLine().split(",");
            List<Double> listaDeLaFila = conversorAListaDoubleAux(filaDividida);
            String claseDeFlor = filaDividida[filaDividida.length-1];
            int indiceNumClase = elegirNumClaseAux(tablaConIndices, claseDeFlor);
            RowWithLabel fila = new RowWithLabel(listaDeLaFila, indiceNumClase);
            tablaConIndices.addRowWithLabels(fila);
        } //Hemos añadido todas las columnas con número de clase a la tabla

        sc.close();
        return tablaConIndices;
    }

    private int elegirNumClaseAux(TableWithLabels tablaConIndices, String claseDeFlor){ //Método auxiliar. Elige el número de clase que le corresponde a una flor dada y, si este no existía, lo introduce en el mapa de la Tabla Con Etiquetas dada.
        int indiceNumClase = tablaConIndices.labelsToIndex.keySet().size();
        if(tablaConIndices.labelsToIndex.containsKey(claseDeFlor)){
            indiceNumClase=tablaConIndices.labelsToIndex.get(claseDeFlor);
        } else {
            tablaConIndices.labelsToIndex.put(claseDeFlor,indiceNumClase);
        }
        return indiceNumClase;
    }



}
