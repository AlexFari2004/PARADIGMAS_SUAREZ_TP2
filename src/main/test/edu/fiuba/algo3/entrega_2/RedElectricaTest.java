package edu.fiuba.algo3.entrega_2;

import edu.fiuba.algo3.modelo.RedElectrica;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class RedElectricaTest {

    @Test
    void AlConsumirEnergiaSeReduce() {
        RedElectrica red = new RedElectrica(5);
        red.consumirEnergia(2);
        assertEquals(3, red.obtenerEnergia());
    }

    @Test
    void AlAgotarLaRedElectricaNoSeReduce() {
        RedElectrica red = new RedElectrica(1);
        red.consumirEnergia(5);
        assertEquals(0, red.obtenerEnergia());
        assertFalse(red.estaAgotada());
    }

}
