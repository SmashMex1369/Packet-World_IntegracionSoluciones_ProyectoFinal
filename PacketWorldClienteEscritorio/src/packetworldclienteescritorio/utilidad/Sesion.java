package packetworldclienteescritorio.utilidad;

import packetworldclienteescritorio.pojo.Colaborador;

/**
 *
 * @author alex4
 */

public class Sesion {

    private static Colaborador colaborador;

    private Sesion() {}

    public static void iniciarSesion(Colaborador c) {
        colaborador = c;
    }

    public static Colaborador getColaborador() {
        return colaborador;
    }

    public static void cerrarSesion() {
        colaborador = null;
    }
}
