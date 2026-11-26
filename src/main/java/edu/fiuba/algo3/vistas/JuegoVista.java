
package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class JuegoVista {

    private final BorderPane root;

    private final TableroVista tableroVista;
    private final BotonVista botonesVista;
    private final BarraBotonesVista barraBotonesVista;
    private final InfoVista infoVista;

    public JuegoVista(Tablero tablero) {

        this.root = new BorderPane();

        this.tableroVista = new TableroVista(tablero);
        this.botonesVista = new BotonVista();
        this.barraBotonesVista = new BarraBotonesVista(botonesVista);
        this.infoVista = new InfoVista();

        VBox panelDerecho = new VBox();

        panelDerecho.setPrefSize(598, 768);
        panelDerecho.setMinSize(598, 768);
        panelDerecho.setMaxSize(598, 768);

        infoVista.getRoot().setPrefSize(598, 450);
        barraBotonesVista.getRoot().setPrefSize(598, 318);

        panelDerecho.getChildren().addAll(infoVista.getRoot(), barraBotonesVista.getRoot());

        HBox contenido = new HBox();

        contenido.getChildren().addAll(tableroVista.getRoot(), panelDerecho);

        root.setCenter(contenido);
    }

    public BorderPane getRoot() {
        return root;
    }
}
