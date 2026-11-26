package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class PartidaVista {

    private final BorderPane root;

    public PartidaVista(TableroVista vistaTablero) {

        this.root = new BorderPane();

        HBox contenido = new HBox();
        contenido.setAlignment(Pos.CENTER);
        contenido.setSpacing(20);
        contenido.setPadding(new Insets(15));

        contenido.getChildren().add(vistaTablero.getRoot());

        VBox panelDerecho = crearPanelDerecho();

        contenido.getChildren().add(panelDerecho);

        root.setCenter(contenido);
    }

    private VBox crearPanelDerecho() {

        VBox panel = new VBox(20);
        panel.setPadding(new Insets(15));
        panel.setPrefWidth(350);
        panel.setAlignment(Pos.TOP_CENTER);

        Label informacion = new Label("INFORMACIÓN DE PARTIDA\n\nTurno: 1 / 5\nEnergía: 7");

        Label mechs = new Label("MECHS\n\nMech 1\nMech 2\nMech 3");

        Label acciones = new Label("ACCIONES\n\nMover\nAtacar\nDeshacer");

        Label finalizarTurno = new Label("FINALIZAR TURNO");

        panel.getChildren().addAll(informacion, mechs, acciones, finalizarTurno);

        return panel;
    }

    public BorderPane getRoot() {
        return root;
    }
}