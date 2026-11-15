package edu.fiuba.algo3.entrega_1;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.mock.TableroStub;
import edu.fiuba.algo3.modelo.unidad.mech.MechDeArtilleria;
import edu.fiuba.algo3.modelo.unidad.arma.Artilleria;
import edu.fiuba.algo3.modelo.unidad.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArtilleriaTest {

    private TableroStub tablero;

    @BeforeEach
    void setUp() {
        tablero = new TableroStub();
    }

    @Test
    public void test01ArtilleriaNoPuedeAtacarCasillasAdyacentes() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        Posicion posMech = new Posicion(3, 3);
        Posicion posAdyacente = new Posicion(3, 4); // Casilla adyacente (prohibido para artillería)

        tablero.obtenerCasilla(posMech).colocarUnidad(mech);
        mech.setPosicion(posMech);

        assertThrows(IllegalArgumentException.class, () -> {
            mech.atacar(posAdyacente, tablero);
        });
    }

    @Test
    public void test02ArtilleriaDaniaObjetivoYEmpujaHaciaAfueraLas4CasillasAdyacentes() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        Saltador vekObjetivo = new Saltador();
        Saltador vekArriba = new Saltador();
        Saltador vekAbajo = new Saltador();
        Saltador vekIzquierda = new Saltador();
        Saltador vekDerecha = new Saltador();

        Posicion posMech = new Posicion(1, 3);
        Posicion posObjetivo = new Posicion(4, 3);

        Posicion posArriba = new Posicion(3, 3);
        Posicion posAbajo = new Posicion(5, 3);
        Posicion posIzquierda = new Posicion(4, 2);
        Posicion posDerecha = new Posicion(4, 4);

        tablero.obtenerCasilla(posMech).colocarUnidad(mech);
        tablero.obtenerCasilla(posObjetivo).colocarUnidad(vekObjetivo);
        tablero.obtenerCasilla(posArriba).colocarUnidad(vekArriba);
        tablero.obtenerCasilla(posAbajo).colocarUnidad(vekAbajo);
        tablero.obtenerCasilla(posIzquierda).colocarUnidad(vekIzquierda);
        tablero.obtenerCasilla(posDerecha).colocarUnidad(vekDerecha);

        // Act
        mech.atacar(posObjetivo, tablero);

        // Assert
        assertTrue(vekObjetivo.obtenerVida() < 2, "La casilla objetivo principal debe recibir daño.");
        assertFalse(tablero.obtenerCasilla(posArriba).estaOcupado(), "La adyacente superior debe quedar vacía por el empuje.");
        assertFalse(tablero.obtenerCasilla(posAbajo).estaOcupado(), "La adyacente inferior debe quedar vacía por el empuje.");
        assertFalse(tablero.obtenerCasilla(posIzquierda).estaOcupado(), "La adyacente izquierda debe quedar vacía por el empuje.");
        assertFalse(tablero.obtenerCasilla(posDerecha).estaOcupado(), "La adyacente derecha debe quedar vacía por el empuje.");
    }
}