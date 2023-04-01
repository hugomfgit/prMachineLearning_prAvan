package es.uji.al415617;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecSys {

    Algorithm algoritmo;
    Table tablaDeDatos;
    List<String> nombresDeItems;
    Map<Integer, Integer> mapaGrupos;

    List<Integer> indicesSimilares;

    public RecSys(Algorithm algorithm){
        this.algoritmo=algorithm;
    }

    public void train(Table trainData) throws ExceptionMoreGroupsThanData {
        this.algoritmo.train(trainData);
    }

    public void run(Table testData, List<String> testItemNames){
        this.tablaDeDatos=testData;
        this.nombresDeItems=testItemNames;
        estimate();
    }

    public List<String> recommend(String nameLikedItem, int numRecommendations){
        int indiceNombreDado = findName(nameLikedItem);
        Row filaDelNombre = tablaDeDatos.filas.get(indiceNombreDado);
        int indiceCentroide = (int) algoritmo.estimate(filaDelNombre.data);
        selectItems(indiceNombreDado, indiceCentroide, numRecommendations);
        return getNamesSelectedItems();
    }

    private void estimate(){
        for(int indice=0; indice<tablaDeDatos.filas.size(); indice++){
            mapaGrupos.put(indice, (Integer) algoritmo.estimate(tablaDeDatos.filas.get(indice)));
        }
    }

    private int findName(String nameItem){
        int indice=-1;
        for(int i=0; indice<nombresDeItems.size(); indice++)
            if(nombresDeItems.get(indice).equals(nameItem))
                indice=i;
        return indice;
    }

    private void selectItems(int idxLikedItem, int labelLikedItem, int numRec){
        for(int indice=0; indice<tablaDeDatos.filas.size(); indice++){
            if(labelLikedItem==((int) algoritmo.estimate(tablaDeDatos.filas.get(indice).data)) & numRec!=0 & idxLikedItem!=indice){
                indicesSimilares.add(indice);
                numRec--;
            }
        }
    }
    private List<String> getNamesSelectedItems(){
        List<String> nombresSeleccionados = new ArrayList<>();
        for(int indice:indicesSimilares)
            nombresSeleccionados.add(nombresDeItems.get(indice));
        return nombresSeleccionados;
    }

}
