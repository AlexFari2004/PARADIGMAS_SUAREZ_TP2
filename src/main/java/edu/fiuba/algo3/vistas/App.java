package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.SystemInfo;
import edu.fiuba.algo3.modelo.Juego;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * JavaFX App
 */
public class App extends Application {
    private Stage stage;
    private Scene escenaPrincipal;
    private Juego juego;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        stage.setTitle("TP2: Into the breach");

        stage.setResizable(true);
        stage.setResizable(true);

        stage.setMinWidth(1200);
        stage.setMinHeight(700);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}