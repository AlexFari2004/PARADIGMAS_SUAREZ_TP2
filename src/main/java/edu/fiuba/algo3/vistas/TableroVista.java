package edu.fiuba.algo3.vistas;

import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.Map;

public class TableroVista {

    private static final int FILAS = 8;
    private static final int COLUMNAS = 8;
    private static final int TAMAÑO_CASILLA = 96;
    private static final int DIMENSION_TABLERO = 768;

    private final GridPane root;
    private final Tablero tablero;
    private final Map<TipoCasilla, Image> imagenesTerrenos;

    public TableroVista(Tablero tablero) {

        this.tablero = tablero;
        this.root = new GridPane();

        this.imagenesTerrenos = Map.of(
                TipoCasilla.SUELO, obtenerImagen("/Imagenes/Suelo.png"),
                TipoCasilla.EDIFICIO, obtenerImagen("/Imagenes/Edificio.png"),
                TipoCasilla.MONTANIA, obtenerImagen("/Imagenes/Montaña.png"),
                TipoCasilla.AGUA, obtenerImagen("/Imagenes/Agua.png"),
                TipoCasilla.ACIDO, obtenerImagen("/Imagenes/Acido.png")
        );

        root.setPrefSize(DIMENSION_TABLERO, DIMENSION_TABLERO);

        cargarTablero();
    }

    private Image obtenerImagen(String ruta) {

        URL imagen = getClass().getResource(ruta);

        return new Image(imagen.toExternalForm());
    }

    private void cargarTablero() {

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                Posicion posicion = new Posicion(fila, columna);
                TipoCasilla tipo = tablero.obtenerCasilla(posicion).getTipo();

                Image imagen = imagenesTerrenos.get(tipo);
                ImageView imagenVista = new ImageView(imagen);

                imagenVista.setFitWidth(TAMAÑO_CASILLA);
                imagenVista.setFitHeight(TAMAÑO_CASILLA);
                imagenVista.setPreserveRatio(false);

                root.add(imagenVista, columna, fila);
            }
        }
    }

    public GridPane getRoot() {
        return root;
    }
}
