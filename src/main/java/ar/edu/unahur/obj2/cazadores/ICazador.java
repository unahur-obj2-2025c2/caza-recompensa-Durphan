package ar.edu.unahur.obj2.cazadores;

import ar.edu.unahur.obj2.profugos.IProfugo;
import ar.edu.unahur.obj2.zonas.IZona;

public interface ICazador {
    boolean puedeCapturar(IProfugo profugo);

    boolean condicionDeCapturaGeneral(IProfugo profugo);

    IZona getZona();

    void asignarZona(IZona zona);

    void hacerPatrullaje();

    void intentarCapturar(IProfugo profugo);

}
