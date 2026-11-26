package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class BarraBotonesVista {

    private final VBox root;

    private final Button botonMover;
    private final Button botonAtacar;
    private final Button botonDeshacer;
    private final Button botonFinalizarTurno;

    public BarraBotonesVista(BotonVista botonVista) {

        this.root = new VBox(15);

        this.botonMover = botonVista.crearBoton("Mover");
        this.botonAtacar = botonVista.crearBoton("Atacar");
        this.botonDeshacer = botonVista.crearBoton("Deshacer");
        this.botonFinalizarTurno = botonVista.crearBoton("Finalizar turno");

        root.setAlignment(Pos.CENTER);
        root.setPrefSize(598, 318);
        root.setStyle("-fx-background-color: #DADADA;");
        root.getChildren().addAll(botonMover, botonAtacar,
                botonDeshacer, botonFinalizarTurno);
    }

    public VBox getRoot() {
        return root;
    }
}
