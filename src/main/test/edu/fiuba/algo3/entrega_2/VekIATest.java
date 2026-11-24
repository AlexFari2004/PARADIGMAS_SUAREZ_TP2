package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekSaltador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class VekIATest {

    private Tablero tablero;

    @BeforeEach
    void setUp() {
        tablero = new Tablero();
    }

    private Posicion posicion(int fila, int columna) {
        return new Posicion(fila, columna);
    }

    // Prioridad 1: atacar un Mech gana sobre atacar un edificio
    @Test
    void prefiereAtacarMechEnVezDeAtacarEdificio() {
        Vek vek = new VekSaltador();
        Mech mech = new MechCombate(new Punio());
        tablero.colocar(vek, posicion(1, 1));
        tablero.colocar(mech, posicion(1, 3));
        int vidaMech = mech.obtenerVida();
        TipoCasilla edificio = tablero.obtenerEdificio(new Posicion(8, 8));

        vek.planificarAccion(tablero);
        vek.resolverAtaque(tablero);

        assertEquals(vidaMech - 2, mech.obtenerVida());
    }
}