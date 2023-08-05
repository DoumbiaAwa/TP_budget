package com.budget.tp_budget.ExecpionErreur;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GestionErreur {

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    // Cette annotation définit le statut de la réponse HTTP en cas de levée de l'exception. Dans ce cas, une exception de validation des arguments entraînera une réponse avec le statut "400 Bad Request".
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleInvalidArgument(MethodArgumentNotValidException ex) {
        // Crée une nouvelle instance de HashMap pour stocker les détails des erreurs de validation.
        Map<String, String> erreur = new HashMap<>();

        // Récupère toutes les erreurs de validation à partir de l'exception MethodArgumentNotValidException.
        ex.getBindingResult().getAllErrors().forEach(error -> {
            // Pour chaque erreur, extrait le nom du champ invalide (FieldError) et le message d'erreur associé.
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();

            // Ajoute le nom du champ invalide et le message d'erreur associé à la map erreur.
            erreur.put(fieldName, errorMessage);
        });

        // Renvoie une ResponseEntity contenant la map erreur, et le statut HTTP "400 Bad Request".
        // Cela permet de renvoyer les détails spécifiques des erreurs de validation dans le corps de la réponse HTTP, au format JSON.
        return new ResponseEntity<>(erreur, HttpStatus.BAD_REQUEST);
    }


    // Cette annotation indique que le résultat de la méthode doit être écrit directement dans le corps de la réponse HTTP.
    @ResponseBody
// Cette annotation définit le statut de la réponse HTTP en cas de levée d'exception.
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
// Cette annotation spécifie que la méthode gère les exceptions de type Exception (et ses sous-classes).
    @ExceptionHandler(Exception.class)
    public Map<String, String> globalException(Exception ex) {
        // Crée une nouvelle instance de HashMap pour stocker les détails de l'exception.
        Map<String, String> erreur = new HashMap<>();

        // Récupère le message de l'exception et l'ajoute à la map sous la clé "message".
        erreur.put("message", ex.getMessage());

        // Renvoie la map contenant le message de l'exception. Cette map sera convertie en JSON et inclus dans le corps de la réponse HTTP.
        return erreur;
    }
}
