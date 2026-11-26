package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekLuciernaga;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class TestVekLuciernaga {
    @Test
    void luciernagaAtacaEnLineaRectaConUnoDeDanio() {
        Tablero tablero = new Tablero();

        VekLuciernaga vek = new VekLuciernaga();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = mech.obtenerVida();

        tablero.colocar(vek, new Posicion(1, 0));
        tablero.colocar(mech, new Posicion(1, 3));

        vek.anunciarAtaque(Direccion.DERECHA).resolver(tablero);
        int vidaTrasPrimerAtaque = mech.obtenerVida();
        assertEquals(1, vidaInicial - vidaTrasPrimerAtaque);

    }

    @Test
    void ataqueDeVekLuciernagaSeDetieneEnMontaniaYNoAlcanzaObjetivoDetras() {
        Tablero tablero = new Tablero();

        VekLuciernaga vek = new VekLuciernaga();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = mech.obtenerVida();

        Posicion posicionMontania = new Posicion(4, 2);
        tablero.colocar(vek, new Posicion(4, 0));
        tablero.colocar(mech, new Posicion(4, 5));

        vek.anunciarAtaque(Direccion.DERECHA).resolver(tablero);

        assertEquals(vidaInicial, mech.obtenerVida());
    }

    @Test
    void luciernagaSeDesplazaPorSueloYNoIncluyeCasillasDeObstaculos() {
        Tablero tablero  = new Tablero();
        VekLuciernaga vek = new VekLuciernaga();
        Posicion posicion = new Posicion(1, 3);
        tablero.colocar(vek, posicion);

        List<Posicion> alcanzables = vek.casillasAlcanzables(tablero);
        Posicion posicionMontania = new Posicion(0, 3);
        Posicion posicionEdificio = new Posicion(1, 5);
        assertFalse(alcanzables.contains(posicionMontania));
        assertFalse(alcanzables.contains(posicionEdificio));

        Posicion posicionDestino = new Posicion(1, 4);
        vek.mover(tablero, posicionDestino);

        assertEquals(posicionDestino, vek.obtenerPosicion());
        assertFalse(tablero.obtenerCasilla(posicion).estaOcupada());
    }
}
