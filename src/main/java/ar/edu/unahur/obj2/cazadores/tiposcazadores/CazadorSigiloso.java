package ar.edu.unahur.obj2.cazadores.tiposcazadores;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class CazadorSigiloso implements ITipoCazador {
    private static final CazadorSigiloso instancia = new CazadorSigiloso();

    public static CazadorSigiloso getInstancia() {
        return instancia;
    }

    @Override
    public void realizarIntimidacionEspecifica(IProfugo profugo) {
        profugo.calmar();
    }

    @Override
    public boolean condicionDeCapturaEspecifica(IProfugo profugo) {
        return profugo.getInocencia() < 50;
    }

    private CazadorSigiloso() {
    }

}
