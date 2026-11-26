package edu.fiuba.algo3.vistas;

import javafx.scene.control.Button;

public class BotonVista {

    public Button crearBoton(String texto) {

        Button boton = new Button(texto);

        boton.setPrefWidth(250);
        boton.setPrefHeight(45);

        boton.setStyle("-fx-font-size: 16px;" + "-fx-font-weight: bold;" +
                        "-fx-background-color: #A68BC8;");

        return boton;
    }
}