package com.groupeisi.backendadmin.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.time.LocalDateTime;

/**
 * APIExceptionHandler est la classe qui nous permet :
 * de gestionnaire d'exceptions centralisé pour l'application.
 * d'intercepter les exceptions qui surviennent au niveau des contrôleurs de l'API.
 * de créer des réponses HTTP cohérentes et informatives pour les clients en cas d'erreurs.
 */

@ControllerAdvice //  est une annotation qui permet de définir des conseils globaux pour les contrôleurs. Elle est utilisée pour centraliser la gestion des exceptions à travers toute l'application.
public class APIExceptionHandler {

    @ExceptionHandler(value = {RequestException.class}) // @ExceptionHandler est utilisée pour définir une méthode qui gère une exception spécifique. Dans ce cas, on gère "RequestException"
    public ResponseEntity<APIException> handleRequestException(RequestException e) {
        APIException exception = new APIException(e.getMessage(), e.getStatus(), LocalDateTime.now());
        return new ResponseEntity<>(exception, e.getStatus());
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<APIException> handleEntityNotFoundException(EntityNotFoundException e) {
        APIException exception = new APIException(e.getMessage(), HttpStatus.NOT_FOUND, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NumberFormatException.class})
    public ResponseEntity<APIException> handleNumberFormatException(NumberFormatException e) {
        APIException exception = new APIException(HttpStatus.BAD_REQUEST.getReasonPhrase(), HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIException> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        APIException exception = new APIException("the input provided is invalid", HttpStatus.BAD_REQUEST, LocalDateTime.now());
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

}