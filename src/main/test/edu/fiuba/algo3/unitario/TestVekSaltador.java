package edu.fiuba.algo3.unitario;

import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.*;

public class TestVekSaltador {
    @Test
    void saltadorMuerdeCasillaAdyacenteConDosDeDanio() {
        Tablero tablero  = new Tablero();
        VekSaltador vek = new VekSaltador();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = mech.obtenerVida();

        tablero.colocar(vek, new Posicion(1, 2));
        tablero.colocar(mech, new Posicion(1, 3));

        vek.anunciarAtaque(Direccion.DERECHA).resolver(tablero);

        assertTrue(mech.obtenerVida() < vidaInicial);
    }

    @Test
    void vekSaltadorNoPuedeAtacarAObjetivoFueraDeRango() {
        Tablero tablero  = new Tablero();
        VekSaltador saltador = new VekSaltador();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicial = mech.obtenerVida();

        tablero.colocar(saltador, new Posicion(1, 1));
        tablero.colocar(mech, new Posicion(1, 3));

        saltador.anunciarAtaque(Direccion.DERECHA).resolver(tablero);

        assertEquals(vidaInicial, mech.obtenerVida());
    }

    @Test
    void saltadorSeMuevePorSueloNoPuedeTransitarNiOcuparMontaniaYEdificio() {
        Tablero tablero  = new Tablero();
        VekSaltador saltador = new VekSaltador();
        Posicion posicion = new Posicion(1, 3);
        tablero.colocar(saltador, posicion);

        List<Posicion> alcanzables = saltador.casillasAlcanzables(tablero);
        Posicion posicionMontania = new Posicion(0, 3);
        Posicion posicionEdificio = new Posicion(1, 5);
        assertFalse(alcanzables.contains(posicionMontania));
        assertFalse(alcanzables.contains(posicionEdificio));

        Posicion posicionDestino = new Posicion(1, 4);
        saltador.mover(tablero, posicionDestino);

        assertEquals(posicionDestino, saltador.obtenerPosicion());
        assertFalse(tablero.obtenerCasilla(posicion).estaOcupada());
    }
}
