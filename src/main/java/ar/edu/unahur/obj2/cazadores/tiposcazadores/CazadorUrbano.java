package ar.edu.unahur.obj2.cazadores.tiposcazadores;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class CazadorUrbano implements ITipoCazador {
    private static final CazadorUrbano instancia = new CazadorUrbano();

    public static CazadorUrbano getInstancia() {
        return instancia;
    }

    @Override
    public void realizarIntimidacionEspecifica(IProfugo profugo) {
        profugo.disminuirHabilidad(5);
    }

    @Override
    public boolean condicionDeCapturaEspecifica(IProfugo profugo) {
        return !profugo.estaNervioso();
    }

    private CazadorUrbano() {
    }

}
