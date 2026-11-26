package edu.fiuba.algo3.vistas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class RegistrarJugadorVista {

    private final Stage stage;
    private final VBox root;
    private final TextField nombreJugador;
    private final Button botonIniciarPartida;

    public RegistrarJugadorVista(Stage ventanaInicio) {

        this.stage = new Stage();
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.initOwner(ventanaInicio);
        this.stage.setResizable(false);
        this.stage.setTitle("Ingresar nombre");

        this.root = new VBox(20);
        this.root.setPadding(new Insets(20));
        this.root.setAlignment(Pos.CENTER);

        Label descripcion = new Label("Ingrese su nombre de jugador");

        this.nombreJugador = new TextField();
        this.nombreJugador.setPromptText("Nombre del jugador");

        this.botonIniciarPartida = new Button("Iniciar partida");

        this.botonIniciarPartida.setStyle("-fx-font-size: 18px; " + "-fx-font-weight: bold; " +
                "-fx-background-color: #A68BC8; " + "-fx-padding: 5 20 5 20;");

        this.root.getChildren().addAll(descripcion, nombreJugador, botonIniciarPartida);

        Scene scene = new Scene(root, 300, 150);
        this.stage.setScene(scene);
    }

    public void mostrarVentanaRegistro() {
        stage.showAndWait();
    }

    public String getNombreJugador() {
        return nombreJugador.getText();
    }

    public Button getBotonIniciarPartida() {
        return botonIniciarPartida;
    }

    public Stage getStage() {
        return stage;
    }
}