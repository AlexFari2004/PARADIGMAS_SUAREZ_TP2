package edu.fiuba.algo3.modelo.ataque;

import edu.fiuba.algo3.modelo.tablero.Tablero;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AtaqueRegistrado {
    private final List<AtaqueAnunciado> anuncios = new ArrayList<>();

    public void registrar(AtaqueAnunciado anuncio) {
        if (anuncio != null) {
            anuncios.add(anuncio);
        }
    }

    public List<AtaqueAnunciado> obtenerTodos() {
        return Collections.unmodifiableList(anuncios);
    }

    public void limpiar() {
        anuncios.clear();
    }

    public boolean estaVacio() {
        return anuncios.isEmpty();
    }

    public void resolverTodos(Tablero tablero) {
        List<AtaqueAnunciado> aResolver = new ArrayList<>(anuncios);
        anuncios.clear();

        for (AtaqueAnunciado anuncio : aResolver) {
            anuncio.resolver(tablero);
        }
    }
}
