package edu.fiuba.algo3.modelo;

public class Logger {
    private static boolean enabled = true;

    public static void log(String mensaje) {
        if (enabled) {
            System.out.println(mensaje);
        }
    }

    public static void setEnabled(boolean enabled) {
        Logger.enabled = enabled;
    }
}
