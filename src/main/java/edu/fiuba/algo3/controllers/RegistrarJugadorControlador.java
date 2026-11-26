package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.vistas.RegistrarJugadorVista;
import javafx.scene.control.Alert;

import java.util.Optional;

public class RegistrarJugadorControlador {

    private static final int MAX_CARACTERES = 15;

    private final RegistrarJugadorVista vista;
    private Optional<String> nombreJugador = Optional.empty();

    public RegistrarJugadorControlador(RegistrarJugadorVista vista) {
        this.vista = vista;
        setupEventHandlerJugar();
    }

    private void setupEventHandlerJugar() {
        vista.getBotonIniciarPartida().setOnAction(event -> handleIniciarPartida());
    }

    private void handleIniciarPartida() {

        String nombre = vista.getNombreJugador().trim();

        if (esNombreValido(nombre)) {
            nombreJugador = Optional.of(nombre);
            vista.getStage().close();
        } else {
            mostrarError("Ingrese un nombre válido");
        }
    }

    private boolean esNombreValido(String nombre) {
        return !nombre.isBlank() && nombre.length() <= MAX_CARACTERES;
    }

    private void mostrarError(String mensaje) {

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(vista.getStage());
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public Optional<String> obtenerNombreJugador() {
        return nombreJugador;
    }
}
