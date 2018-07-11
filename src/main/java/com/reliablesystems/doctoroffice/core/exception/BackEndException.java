package com.reliablesystems.doctoroffice.core.exception;

public class BackEndException extends RuntimeException {

    /**
     * Crea una excepcion de back end del framework
     */
    public BackEndException() {
        super();
    }


    /**
     * Crea una excepcion de back end del framework con un mensaje
     *
     * @param msg Mensaje personalizado de la excepcion
     */
    public BackEndException(String msg) {
        super(msg);
    }


    /**
     * Crea una excepcion del back end del framework con un mensaje personalizado a partir de otra excepcion
     *
     * @param msg    Mensaje personalizado de la exepcion
     * @param nested Excepcion que sera guardada dentro de esta
     */
    public BackEndException(String msg, Exception nested) {
        super(msg, nested);
    }
}
