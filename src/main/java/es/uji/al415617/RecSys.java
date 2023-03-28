package es.uji.al415617;

import java.util.List;
import java.util.Map;

public class RecSys {

    Algorithm algoritmo;
    Table tablaDeDatos;
    List<String> nombresDeItems;

    Map<Integer, Integer> mapaGrupos;

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

    }

    private void estimate(){
        for(int indice=0; indice<tablaDeDatos.filas.size(); indice++){
            mapaGrupos.put(indice, (Integer) algoritmo.estimate(tablaDeDatos.filas.get(indice)));
        }
    }

    private int findName(String nameItem);

    private void selectItems(int idxLikedItem, int labelLikedItem, int numRec);

    private List<String> getNamesSelectedItems();

}
