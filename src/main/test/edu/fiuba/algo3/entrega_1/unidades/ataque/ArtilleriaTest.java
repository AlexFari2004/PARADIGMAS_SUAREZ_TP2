package edu.fiuba.algo3.entrega_1.unidades.ataque;

import edu.fiuba.algo3.modelo.Posicion;
import edu.fiuba.algo3.modelo.Tablero;
import edu.fiuba.algo3.modelo.casilla.TipoCasilla;
import edu.fiuba.algo3.modelo.unidad.arma.Artilleria;
import edu.fiuba.algo3.modelo.unidad.mech.MechDeArtilleria;
import edu.fiuba.algo3.modelo.unidad.vek.VekSaltador;
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
        Posicion posAdyacente = new Posicion(3, 4);

        tablero.colocar(mech, posMech);

        assertThrows(IllegalArgumentException.class, () -> {
            mech.atacar(posAdyacente, tablero);
        });
    }

    @Test
    public void test02ArtilleriaEmpujaHaciaArribaLaUnidadAdyacenteHaciaArribaYHace2DeDanioAlObjetivo() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        VekSaltador vekObjetivo = new VekSaltador();
        VekSaltador vekArriba = new VekSaltador();

        Posicion posMech = new Posicion(1, 3);
        Posicion posObjetivo = new Posicion(4, 3);
        Posicion posArriba = new Posicion(3, 3);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekObjetivo, posObjetivo);
        tablero.colocar(vekArriba, posArriba);

        mech.atacar(posObjetivo, tablero);

        assertTrue(vekObjetivo.obtenerVida() < 2);
        assertFalse(tablero.obtenerCasilla(posArriba).estaOcupada());
        assertEquals(vekArriba, tablero.obtenerCasilla(new Posicion(2, 3)).getUnidad());
    }

    @Test
    public void test03ArtilleriaEmpujaHaciaAbajoLaUnidadAdyacenteHaciaAbajoYHace2DeDanioAlObjetivo() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        VekSaltador vekObjetivo = new VekSaltador();
        VekSaltador vekAbajo = new VekSaltador();

        Posicion posMech = new Posicion(1, 3);
        Posicion posObjetivo = new Posicion(4, 3);
        Posicion posAbajo = new Posicion(5, 3);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekObjetivo, posObjetivo);
        tablero.colocar(vekAbajo, posAbajo);

        mech.atacar(posObjetivo, tablero);

        assertTrue(vekObjetivo.obtenerVida() < 2);
        assertFalse(tablero.obtenerCasilla(posAbajo).estaOcupada());
        assertEquals(vekAbajo, tablero.obtenerCasilla(new Posicion(6, 3)).getUnidad());
    }

    @Test
    public void test04ArtilleriaEmpujaHaciaLaIzquierdaLaUnidadAdyacenteALaIzquierdaYHace2DeDanioAlObjetivo() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        VekSaltador vekObjetivo = new VekSaltador();
        VekSaltador vekIzquierda = new VekSaltador();

        Posicion posMech = new Posicion(1, 3);
        Posicion posObjetivo = new Posicion(4, 3);
        Posicion posIzquierda = new Posicion(4, 2);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekObjetivo, posObjetivo);
        tablero.colocar(vekIzquierda, posIzquierda);

        mech.atacar(posObjetivo, tablero);

        assertTrue(vekObjetivo.obtenerVida() < 2);
        assertFalse(tablero.obtenerCasilla(posIzquierda).estaOcupada());
        assertEquals(vekIzquierda, tablero.obtenerCasilla(new Posicion(4, 1)).getUnidad());
    }

    @Test
    public void test05ArtilleriaEmpujaHaciaLaDerechaLaUnidadAdyacenteALaDerechaYHace2DeDanioAlObjetivo() {
        Artilleria artilleria = new Artilleria();
        MechDeArtilleria mech = new MechDeArtilleria(artilleria);

        VekSaltador vekObjetivo = new VekSaltador();
        VekSaltador vekDerecha = new VekSaltador();

        Posicion posMech = new Posicion(1, 3);
        Posicion posObjetivo = new Posicion(4, 3);
        Posicion posDerecha = new Posicion(4, 4);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekObjetivo, posObjetivo);
        tablero.colocar(vekDerecha, posDerecha);

        mech.atacar(posObjetivo, tablero);

        assertTrue(vekObjetivo.obtenerVida() < 2);
        assertFalse(tablero.obtenerCasilla(posDerecha).estaOcupada());
        assertEquals(vekDerecha, tablero.obtenerCasilla(new Posicion(4, 5)).getUnidad());
    }

    @Test
    public void test06ArtilleriaAtacaPorEncimaDeObstaculos() {
        MechDeArtilleria mech = new MechDeArtilleria(new Artilleria());
        VekSaltador vekObjetivo = new VekSaltador();
        Posicion posMech = new Posicion(0, 2);
        Posicion posObjetivo = new Posicion(0, 4);

        tablero.colocar(mech, posMech);
        tablero.colocar(vekObjetivo, posObjetivo);

        mech.atacar(posObjetivo, tablero);

        assertEquals(TipoCasilla.MONTAÑA, tablero.obtenerCasilla(new Posicion(0, 3)).getTipo());
        assertEquals(1, vekObjetivo.obtenerVida());
    }
}