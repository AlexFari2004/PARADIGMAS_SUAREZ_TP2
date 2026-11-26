package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Edificio;
import edu.fiuba.algo3.modelo.tablero.Posicion;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import edu.fiuba.algo3.modelo.tablero.TipoCasilla;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EdificioTest {
    @Test
    void recibirDanioReduceLaRedEnUnPunto() {
        RedElectrica red = new RedElectrica(5);
        Tablero tablero = new Tablero(red);
        Posicion posicionEdificio = new Posicion(1, 5);
        Edificio edificio =  (Edificio) tablero.obtenerCasilla(posicionEdificio);


        edificio.recibirDanio(3, tablero,posicionEdificio);

        assertEquals(4, red.obtenerEnergia());
        assertEquals(TipoCasilla.SUELO, tablero.obtenerCasilla(posicionEdificio).getTipo());
    }
}
