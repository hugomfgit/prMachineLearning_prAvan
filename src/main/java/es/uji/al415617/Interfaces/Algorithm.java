package es.uji.al415617.Interfaces;

import es.uji.al415617.Exceptions.ExceptionMoreGroupsThanData;
import es.uji.al415617.Composition.Tables.Table;

public interface Algorithm<T extends Table, R, P> {

    void train(T tabla) throws ExceptionMoreGroupsThanData;

    R estimate(P listado);

}
