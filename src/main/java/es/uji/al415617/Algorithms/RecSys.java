package es.uji.al415617.Algorithms;

import es.uji.al415617.Composition.Tables.Table;
import es.uji.al415617.Exceptions.ExceptionMoreGroupsThanData;
import es.uji.al415617.Interfaces.Algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecSys {

    Algorithm algoritmo;
    Table tablaDeDatos;
    List<String> nombresDeItems; //Títulos.
    Map<Integer, Integer> mapaGrupos;

    List<Integer> indicesSimilares;

    public RecSys(Algorithm algorithm){
        this.algoritmo=algorithm;
        this.mapaGrupos = new HashMap<>();
    }

    public void train(Table trainData) throws ExceptionMoreGroupsThanData {
        this.algoritmo.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames){
        this.tablaDeDatos = testData;
        this.nombresDeItems = testItemNames;
        this.estimate();
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations){
        int indiceNombreDado = findName(nameLikedItem);
        int indiceCentroide = mapaGrupos.get(indiceNombreDado);
        selectItems(indiceNombreDado, indiceCentroide, numRecommendations);
        return getNamesSelectedItems();
    }

    private void estimate(){
        for(int indice=0; indice<tablaDeDatos.filas.size(); indice++){
            mapaGrupos.put(indice, (Integer) algoritmo.estimate(tablaDeDatos.filas.get(indice)));
        }
    }

    private int findName(String nameItem){
        return nombresDeItems.indexOf(nameItem);
    }

    private void selectItems(int idxLikedItem, int labelLikedItem, int numRec){
        indicesSimilares = new ArrayList<>();
        for(int indice=0; indice<tablaDeDatos.filas.size(); indice++){
            if(labelLikedItem == mapaGrupos.get(indice) & idxLikedItem!=indice){
                indicesSimilares.add(indice);
            }
            if(numRec<=indicesSimilares.size())
                break;
        }
    }
    private List<String> getNamesSelectedItems(){
        List<String> nombresSeleccionados = new ArrayList<>();
        for(int indice:indicesSimilares)
            nombresSeleccionados.add(nombresDeItems.get(indice));
        return nombresSeleccionados;
    }

}
