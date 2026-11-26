package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.ataque.RegistroAtaque;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.Emergencia;
import edu.fiuba.algo3.modelo.turno.FaseResolucion;
import edu.fiuba.algo3.modelo.turno.FaseVek;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.turno.GestorEmergencia;
import edu.fiuba.algo3.modelo.vek.*;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FaseVekTest {

    @Test
    void vekAnunciaEnFaseVekYEjecutaEnFaseResolucion() {
        Tablero tablero = new Tablero();
        RegistroAtaque registroAtaque = new RegistroAtaque();
        CriterioMenorFila criterio =  new CriterioMenorFila();
        VekIA vekIA = new VekIA(criterio);
        List<Emergencia> emergencias = new ArrayList<>();
        GestorEmergencia gestor = new GestorEmergencia(emergencias);

        VekAvispon vek1 = new VekAvispon();
        Mech mech = new MechCombate(new Punio(1,new SinEfecto()));
        VekSaltador vek2 = new VekSaltador();
        tablero.colocar(vek1, new Posicion(0, 0));
        tablero.colocar(mech, new Posicion(1, 0));
        tablero.colocar(vek2, new Posicion(2, 0));
        int vidaInicial = mech.obtenerVida();

        assertEquals(2, tablero.obtenerVeks().size());

        new FaseVek(tablero, vekIA, registroAtaque,gestor).ejecutar();
        assertEquals(vidaInicial, mech.obtenerVida());
        assertEquals(new Posicion(0, 0),vek1.obtenerPosicion());
        assertEquals(new Posicion(1, 1),vek2.obtenerPosicion());
        new FaseResolucion(tablero, registroAtaque).ejecutar();
        assertEquals(0, mech.obtenerVida());
    }

}


