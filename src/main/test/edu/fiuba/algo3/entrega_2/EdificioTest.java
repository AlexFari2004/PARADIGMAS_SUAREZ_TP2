package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Edificio;
import edu.fiuba.algo3.modelo.ataque.SinEfecto;
import edu.fiuba.algo3.modelo.tablero.Tablero;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EdificioTest {


    @Test
    void recibirDañoReduceLaRedEnUnPunto() {
        RedElectrica red = new RedElectrica(5);
        Edificio edificio = new Edificio();

        edificio.recibirDanio(3, new Tablero(),edificio.getUnidad().obtenerPosicion());

        assertEquals(4, red.obtenerEnergia());
    }

    @Test
    void recibirAtaqueReduceLaRedEnUnPunto() {
        RedElectrica red = new RedElectrica(5);
        Edificio edificio = new Edificio();

        edificio.recibirDanio(3, new Tablero(),edificio.getUnidad().obtenerPosicion());

        assertEquals(4, red.obtenerEnergia());
    }
}
