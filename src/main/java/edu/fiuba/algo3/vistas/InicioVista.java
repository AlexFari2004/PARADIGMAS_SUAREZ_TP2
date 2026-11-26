package edu.fiuba.algo3.vistas;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class InicioVista {

    private static final int RESOLUCION_ANCHO = 1366;
    private static final int RESOLUCION_ALTO = 768;

    private final VBox root;
    private final Button botonJugar;

    public InicioVista() {

        String imagenFondoInicio = getClass().getResource("/Imagenes/inicioFondo.png").toExternalForm();

        Image imagenFondo = new Image(imagenFondoInicio);
        ImageView nodoImagen = new ImageView(imagenFondo);

        nodoImagen.setFitWidth(RESOLUCION_ANCHO);
        nodoImagen.setFitHeight(RESOLUCION_ALTO);

        this.botonJugar = new Button("Jugar");
        botonJugar.setStyle("-fx-background-color: #A68BC8; " + "-fx-font-size: 18px; " +
                        "-fx-font-weight: bold; " + "-fx-padding: 10 20 10 20;");

        StackPane panelInicio = new StackPane(nodoImagen, botonJugar);
        StackPane.setAlignment(botonJugar, Pos.CENTER);

        this.root = new VBox(panelInicio);
    }

    public VBox getRoot() {
        return root;
    }

    public Button getBotonJugar() {
        return botonJugar;
    }
}
