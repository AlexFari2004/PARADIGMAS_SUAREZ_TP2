package edu.fiuba.algo3.controllers;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.vistas.InicioVista;
import edu.fiuba.algo3.vistas.JuegoVista;
import edu.fiuba.algo3.vistas.RegistrarJugadorVista;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicioControlador {

    private static final int ANCHO = 1366;
    private static final int ALTO = 768;

    private final Stage ventanaPrincipal;

    public InicioControlador(InicioVista vista, Stage ventanaPrincipal) {

        this.ventanaPrincipal = ventanaPrincipal;

        vista.getBotonJugar().setOnAction(event -> abrirRegistrarJugador());
    }

    private void abrirRegistrarJugador() {

        RegistrarJugadorVista vistaNombre = new RegistrarJugadorVista(ventanaPrincipal);

        RegistrarJugadorControlador controlador = new RegistrarJugadorControlador(vistaNombre);

        vistaNombre.mostrarVentanaRegistro();

        controlador.obtenerNombreJugador().ifPresent(nombre -> iniciarJuego());
    }

    private void iniciarJuego() {

        Tablero tablero = new Tablero();

        JuegoVista juegoVista = new JuegoVista(tablero);

        Scene escenaJuego = new Scene(juegoVista.getRoot(), ANCHO, ALTO);

        ventanaPrincipal.setScene(escenaJuego);
    }
}
