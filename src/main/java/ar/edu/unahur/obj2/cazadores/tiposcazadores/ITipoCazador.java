package ar.edu.unahur.obj2.cazadores.tiposcazadores;

import ar.edu.unahur.obj2.profugos.IProfugo;

public interface ITipoCazador {

    void realizarIntimidacionEspecifica(IProfugo profugo);

    boolean condicionDeCapturaEspecifica(IProfugo profugo);
}
