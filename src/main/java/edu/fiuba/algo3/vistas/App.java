package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.controllers.InicioControlador;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    private static final int ANCHO = 1366;
    private static final int ALTO = 768;

    @Override
    public void start(Stage stage) {

        stage.setTitle("Into the Breach");

        InicioVista inicioVista = new InicioVista();

        Scene escenaPrincipal = new Scene(inicioVista.getRoot(), ANCHO, ALTO);

        new InicioControlador(inicioVista, stage);

        stage.setScene(escenaPrincipal);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}