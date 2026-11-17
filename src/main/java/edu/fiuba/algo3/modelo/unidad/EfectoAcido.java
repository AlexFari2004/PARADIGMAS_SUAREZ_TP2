package edu.fiuba.algo3.modelo.unidad;

public class EfectoAcido implements EfectoAtaque {
    public void aplicar(Unidad objetivo) { objetivo.contaminar();}
}
