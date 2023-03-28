package es.uji.al415617;

public interface Algorithm<T extends Table, R, P> {

    public void train(T tabla) throws ExceptionMoreGroupsThanData;

    public R estimate(P listado);

}
