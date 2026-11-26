package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.ataque.Direccion;
import edu.fiuba.algo3.modelo.ataque.EfectoEmpuje;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.errores.AccionNoPermitidaException;
import edu.fiuba.algo3.modelo.errores.AtaqueDesdeAguaException;
import edu.fiuba.algo3.modelo.mech.MechCanion;
import edu.fiuba.algo3.modelo.mech.MechCombate;
import edu.fiuba.algo3.modelo.mech.Punio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.vek.VekAvispon;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class MechTest {
    @Test
    void unMechEnAguaNoPuedeAtacar() {
        Tablero tablero = new Tablero();

        Posicion posicion =  new Posicion(0,2);
        Posicion posicionEnAgua =  new Posicion(0,1);

        MechCombate mech = new MechCombate(new Punio(2,new EfectoEmpuje()));

        tablero.colocar(mech, posicion);
        mech.mover(posicionEnAgua ,tablero);

        assertThrows(AtaqueDesdeAguaException.class, () ->mech.atacar(posicion, tablero));


    }
}
