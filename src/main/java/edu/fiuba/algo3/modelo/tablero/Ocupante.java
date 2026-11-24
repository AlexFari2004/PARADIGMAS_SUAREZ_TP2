package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.mech.Direccion;
import edu.fiuba.algo3.modelo.vek.EfectoEmpuje;

public interface Ocupante {
    void recibirDanio(int danio);
    void aplicarEmpuje(EfectoEmpuje empuje, Posicion posicion, Direccion dir, Tablero tablero);
}
