package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.TipoCasilla;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static edu.fiuba.algo3.modelo.TipoCasilla.*;

public class TableroTest {

    @Test
    void test01InicializacionDelTableroConMapaPredefinido() {


        Tablero tablero = new Tablero();

        TipoCasilla[][] tableroPredefinido = {
                {SUELO, AGUA, SUELO, MONTAÑA, SUELO, SUELO, SUELO, SUELO},
                {SUELO, SUELO, SUELO, SUELO, SUELO, EDIFICIO, SUELO, SUELO},
                {SUELO, SUELO, ACIDO, SUELO, AGUA, AGUA, SUELO, SUELO},
                {AGUA, SUELO, SUELO, SUELO, SUELO, SUELO, ACIDO, SUELO},
                {SUELO, SUELO, MONTAÑA, SUELO, SUELO, SUELO, SUELO, MONTAÑA},
                {SUELO, AGUA, SUELO, EDIFICIO, SUELO, SUELO, SUELO, SUELO},
                {SUELO, SUELO, AGUA, SUELO, SUELO, AGUA, SUELO, SUELO},
                {ACIDO, SUELO, SUELO, ACIDO, SUELO, SUELO, SUELO, EDIFICIO}
        };


        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {

                Posicion posicion = new Posicion(fila, columna);

                assertNotNull(
                        tablero.obtenerCasilla(posicion),
                        "La casilla no debe ser null en (" + fila + ", " + columna + ")"
                );

                assertEquals(
                        tableroPredefinido[fila][columna],
                        tablero.obtenerCasilla(posicion).getTipo(),
                        "Tipo de casilla incorrecto en (" + fila + ", " + columna + ")"
                );
            }
        }
    }
}
