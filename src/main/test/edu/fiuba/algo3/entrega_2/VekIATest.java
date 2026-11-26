package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.ataque.RegistroAtaque;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.vek.CriterioMenorFila;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekIA;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class VekIATest {

    @Test
    void priorizaAtacarEdificioAntesQueAUnMech() {
        Tablero tablero = new Tablero();
        RegistroAtaque registroAtaque = new RegistroAtaque();
        CriterioMenorFila criterio = new CriterioMenorFila();
        VekIA vekIA = new VekIA(criterio);


        Posicion posicionEdificio = new Posicion(1, 5);
        Posicion posicionVek = new Posicion(1, 3);
        Posicion posicionMech = new Posicion(1, 2);

        VekSaltador vek = new VekSaltador();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicialMech = mech.obtenerVida();

        tablero.colocar(vek, posicionVek);
        tablero.colocar(mech, posicionMech);

        vekIA.tomarDecision(vek, tablero, registroAtaque);
        registroAtaque.resolverTodos(tablero);

        assertEquals(new Posicion(0, 5), vek.obtenerPosicion());
        assertEquals(TipoCasilla.SUELO, tablero.obtenerCasilla(posicionEdificio).getTipo());
        assertEquals(vidaInicialMech, mech.obtenerVida());
    }

    @Test
    void siNoPuedeAtacarEdificioAtacaAUnMech() {
        Tablero tablero = new Tablero();
        RegistroAtaque registroAtaque = new RegistroAtaque();
        CriterioMenorFila criterio = new CriterioMenorFila();
        VekIA vekIA = new VekIA(criterio);


        Posicion posicionVek = new Posicion(2, 1);
        Posicion posicionMech = new Posicion(3, 3);

        VekSaltador vek = new VekSaltador();
        MechCombate mech = new MechCombate(new Punio(1, new SinEfecto()));
        int vidaInicialMech = mech.obtenerVida();

        tablero.colocar(vek, posicionVek);
        tablero.colocar(mech, posicionMech);

        vekIA.tomarDecision(vek, tablero, registroAtaque);
        registroAtaque.resolverTodos(tablero);

        assertEquals(new Posicion(2, 3), vek.obtenerPosicion());
        assertTrue(mech.obtenerVida() < vidaInicialMech);
    }

    @Test
    void siNoPuedeAtacarNadaSeAcercaAlEdificioMasCercanoSinAnunciarAtaque() {
        Tablero tablero = new Tablero();
        RegistroAtaque registroAtaque = new RegistroAtaque();
        CriterioMenorFila criterio = new CriterioMenorFila();
        VekIA vekIA = new VekIA(criterio);


        Posicion posicionVek = new Posicion(0, 0);
        Posicion posicionEdificio = new Posicion(5, 3);

        VekSaltador vek = new VekSaltador();
        tablero.colocar(vek, posicionVek);

        vekIA.tomarDecision(vek, tablero, registroAtaque);
        registroAtaque.resolverTodos(tablero);

        Posicion posicionCercaAlEdificio = vek.obtenerPosicion();
        assertTrue(posicionCercaAlEdificio.distanciaA(posicionEdificio) < posicionVek.distanciaA(posicionEdificio));
        assertEquals(TipoCasilla.EDIFICIO, tablero.obtenerCasilla(posicionEdificio).getTipo());

    }
}