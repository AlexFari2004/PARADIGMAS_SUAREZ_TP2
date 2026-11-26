package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.errores.MovimientoEnPosicionOcupadaException;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestDireccion {
    @Test
    void desplazarHaciaArribaDecrementaFila() {
        Posicion posicion = new Posicion(3, 3);
        Posicion posicionDesplazado = new  Posicion(2, 3);

        assertEquals(Direccion.ARRIBA.desplazarHacia(posicion), posicionDesplazado);
    }
    @Test
    void desplazarHaciaAbajoIncrementaFila() {
        Posicion posicion = new Posicion(3, 3);
        Posicion posicionDesplazado = new  Posicion(4, 3);

        assertEquals(Direccion.ABAJO.desplazarHacia(posicion), posicionDesplazado);
    }

    @Test
    void desplazarHaciaDerechaIncrementaColumna() {
        Posicion posicion = new Posicion(3, 3);
        Posicion posicionDesplazado = new  Posicion(3, 4);

        assertEquals(Direccion.DERECHA.desplazarHacia(posicion), posicionDesplazado);
    }

    @Test
    void desplazarHaciaIzquierdaDecrementaColumna() {
        Posicion posicion = new Posicion(3, 3);
        Posicion posicionDesplazado = new  Posicion(3, 2);

        assertEquals(Direccion.IZQUIERDA.desplazarHacia(posicion), posicionDesplazado);
    }

    @Test
    void obtenerDireccionesDevuelveLasDireccionesOrtogonales() {
        List<Direccion> direcciones = Direccion.obtenerDirecciones();

        assertEquals(4, direcciones.size());
        assertTrue(direcciones.containsAll(List.of(
                Direccion.ARRIBA,
                Direccion.ABAJO,
                Direccion.DERECHA,
                Direccion.IZQUIERDA
        )));
    }
    @Test
    void reconocerMismoPosicionDebeLanzarExcepcion() {
        Posicion posicion = new Posicion(3, 3);
        Posicion mismaposicion = new  Posicion(3, 3);

        assertThrows(IllegalArgumentException.class, () -> Direccion.reconocerDireccion(posicion, mismaposicion) );

    }
    @Test
    void reconocerDireccionOrtogonalDebeIndicarLaDireccionCorrecta() {
        Posicion posicion = new Posicion(3, 3);

        Posicion posicionArriba  = new Posicion(2, 3);
        Posicion posicionAbajo  = new Posicion(6, 3);
        Posicion posicionDerecha = new Posicion(3, 6);
        Posicion posicionIzquierda = new Posicion(3, 2);

        assertEquals(Direccion.ARRIBA, Direccion.reconocerDireccion(posicion, posicionArriba));
        assertEquals(Direccion.ABAJO, Direccion.reconocerDireccion(posicion, posicionAbajo));
        assertEquals(Direccion.DERECHA, Direccion.reconocerDireccion(posicion, posicionDerecha));
        assertEquals(Direccion.IZQUIERDA, Direccion.reconocerDireccion(posicion, posicionIzquierda));
    }

    @Test
    void reconocerDireccionEnDiagonalEquidistanteIndicaElHorizontal() {
        Posicion posicion = new Posicion(3, 3);

        Posicion posicionDiagonal1 = new Posicion(5, 5);
        Posicion posicionDiagonal2 = new Posicion(1, 1);

        assertEquals(Direccion.DERECHA, Direccion.reconocerDireccion(posicion, posicionDiagonal1 ));
        assertEquals(Direccion.IZQUIERDA, Direccion.reconocerDireccion(posicion, posicionDiagonal2));
    }

    @Test
    void reconocerDireccionEnDiagonalIndicaElDeMayorDistancia() {
        Posicion posicion = new Posicion(3, 3);

        assertEquals(Direccion.DERECHA, Direccion.reconocerDireccion(posicion, new Posicion(5, 7)));
        assertEquals(Direccion.ARRIBA, Direccion.reconocerDireccion(posicion, new Posicion(1, 4)));
    }
}
