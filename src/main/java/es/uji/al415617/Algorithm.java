package es.uji.al415617;

public interface Algorithm<T extends Table, R, P> {

    void train(T tabla) throws ExceptionMoreGroupsThanData;

    R estimate(P listado);

}
