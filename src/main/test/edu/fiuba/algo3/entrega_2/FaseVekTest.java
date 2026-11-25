package edu.fiuba.algo3.entrega_2;


import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.turno.FaseResolucion;
import edu.fiuba.algo3.modelo.turno.FaseVek;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.mech.Mech;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.vek.Vek;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class FaseVekTest {

    @Test
    void vekAnunciaEnFaseVekYEjecutaEnFaseResolucion() {
        Tablero tablero = new Tablero();
        Vek vek1 = new VekAvispon();
        Vek vek2 = new VekAvispon();
        //Mech mech = new MechCombate(new Punio());
        tablero.colocar(vek1, new Posicion(4, 3));
        tablero.colocar(vek2, new Posicion(4, 5));
        //tablero.colocar(mech, new Posicion(4, 4));
        //int vidaInicial = mech.obtenerVida();


        //assertEquals(2, tablero.obtenerVeks().size());
        //new FaseVek(tablero).ejecutar();
       ///assertEquals(vidaInicial, mech.obtenerVida());

        //new FaseResolucion(tablero).ejecutar();
        //assertEquals(vidaInicial - 2, mech.obtenerVida());   // 2 + 2
    }

}


