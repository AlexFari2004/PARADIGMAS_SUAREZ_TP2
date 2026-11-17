package edu.fiuba.algo3.modelo.unidad;

public class EfectoAcido implements EfectoAtaque {
    public void aplicar(Unidad objetivo) {
        System.out.println("SE APLICO EFECTO ACIDO");
        objetivo.contaminar();
    }

}
