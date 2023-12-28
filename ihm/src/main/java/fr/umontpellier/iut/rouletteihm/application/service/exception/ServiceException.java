package fr.umontpellier.iut.rouletteihm.application.service.exception;

public class ServiceException extends Exception {

    // fonction qui permet de lever une exception
    public ServiceException(String message) {
        super(message);
    }
}

