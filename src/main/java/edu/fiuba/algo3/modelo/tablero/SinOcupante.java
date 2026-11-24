package edu.fiuba.algo3.modelo.tablero;

import edu.fiuba.algo3.modelo.mech.Direccion;
import edu.fiuba.algo3.modelo.vek.EfectoEmpuje;

public class SinOcupante implements Ocupante{
    public void recibirDanio(int danio) { }
    public void aplicarEmpuje(EfectoEmpuje e, Posicion p, Direccion d, Tablero t) { }
}
