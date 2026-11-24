package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.RedElectrica;
import edu.fiuba.algo3.modelo.tablero.Edificio;
import edu.fiuba.algo3.modelo.vek.SinEfecto;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class EdificioTest {


    @Test
    void recibirDañoReduceLaRedEnUnPunto() {
        RedElectrica red = new RedElectrica(5);
        Edificio edificio = new Edificio(red);

        edificio.recibirDaño(3);

        assertEquals(4, red.obtenerEnergia());
    }

    @Test
    void recibirAtaqueReduceLaRedEnUnPunto() {
        RedElectrica red = new RedElectrica(5);
        Edificio edificio = new Edificio(red);

        edificio.recibirAtaque(2, new SinEfecto());

        assertEquals(4, red.obtenerEnergia());
    }
}
