package ar.edu.unahur.obj2.cazadores.tiposcazadores;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class CazadorRural implements ITipoCazador {
    private static final CazadorRural instancia = new CazadorRural();

    public static CazadorRural getInstancia() {
        return instancia;
    }

    private CazadorRural() {
    }

    @Override
    public void realizarIntimidacionEspecifica(IProfugo profugo) {
        profugo.ponerNervioso();
    }

    @Override
    public boolean condicionDeCapturaEspecifica(IProfugo profugo) {
        return profugo.estaNervioso();
    }

}
