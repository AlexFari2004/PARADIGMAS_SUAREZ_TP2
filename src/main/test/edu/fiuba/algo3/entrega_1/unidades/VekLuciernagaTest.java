package edu.fiuba.algo3.entrega_1.unidades;

import edu.fiuba.algo3.modelo.unidad.mech.MechCombate;
import edu.fiuba.algo3.modelo.unidad.arma.Puno;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VekLuciernagaTest {
    @Test
    public void contaminarDosVecesNoAcumulaElDaño() {

        MechCombate mech = new MechCombate(new Puno());

        mech.contaminar();
        mech.contaminar();

        mech.recibirDaño(1);
        assertEquals(1, mech.obtenerVida());
    }
}
