package edu.fiuba.algo3.entrega_1.unidades.ataque;

import edu.fiuba.algo3.modelo.*;
import edu.fiuba.algo3.modelo.unidad.mech.MechDeArtilleria;
import edu.fiuba.algo3.modelo.unidad.arma.Artilleria;
import edu.fiuba.algo3.modelo.unidad.vek.Saltador;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArtilleriaTest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    @Test
    public void test01ArtilleriaNoPuedeAtacarCasillasAdyacentes() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        Posicion posMech = new Posicion(3, 3);
        Posicion posAdyacente = new Posicion(3, 4); // Casilla adyacente (prohibido para artillería)

        mech.mover(posMech, tablero);

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

        mech.mover(posMech, tablero);
        vekObjetivo.mover(posObjetivo, tablero);
        vekArriba.mover(posArriba, tablero);
        vekAbajo.mover(posAbajo, tablero);
        vekIzquierda.mover(posIzquierda, tablero);
        vekDerecha.mover(posDerecha, tablero);

        // Act
        mech.atacar(posObjetivo, tablero);

        // Assert
        assertTrue(vekObjetivo.obtenerVida() < 2, "La casilla objetivo principal debe recibir daño.");
        assertFalse(tablero.obtenerCasilla(posArriba).estaOcupada(), "La adyacente superior debe quedar vacía por el empuje.");
        assertFalse(tablero.obtenerCasilla(posAbajo).estaOcupada(), "La adyacente inferior debe quedar vacía por el empuje.");
        assertFalse(tablero.obtenerCasilla(posIzquierda).estaOcupada(), "La adyacente izquierda debe quedar vacía por el empuje.");
        assertFalse(tablero.obtenerCasilla(posDerecha).estaOcupada(), "La adyacente derecha debe quedar vacía por el empuje.");
    }
}