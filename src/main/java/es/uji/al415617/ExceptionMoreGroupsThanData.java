package es.uji.al415617;

public class ExceptionMoreGroupsThanData extends Exception {
    public ExceptionMoreGroupsThanData(){
        super("Error. Se indica un número de grupos mayor al número de datos.");
    }
}
