package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class InfoVista {

    private final VBox root;
    private final TextArea anuncios;

    public InfoVista() {

        this.root = new VBox(12);

        Label titulo = new Label("INFORMACIÓN DE PARTIDA");
        titulo.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label turno = new Label("Turno: 1 / 5");
        Label energia = new Label("Energía: 5");

        Label tituloMechs = new Label("MECHS");
        tituloMechs.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label mech1 = new Label("Mech 1");
        Label mech2 = new Label("Mech 2");
        Label mech3 = new Label("Mech 3");

        Label tituloRegistro = new Label("Anuncios de la partida");
        tituloRegistro.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");

        this.anuncios = new TextArea();
        anuncios.setEditable(false);
        anuncios.setWrapText(true);

        VBox.setVgrow(anuncios, Priority.ALWAYS);

        root.setAlignment(Pos.CENTER);
        root.setPrefSize(598, 450);
        root.setStyle("-fx-background-color: #DADADA; -fx-padding: 20;");

        root.getChildren().addAll(titulo, turno, energia,
                tituloMechs, mech1, mech2, mech3,
                tituloRegistro, anuncios);
    }

    public VBox getRoot() {
        return root;
    }

}
