package ar.edu.unahur.obj2.zonas;

import java.util.List;

import ar.edu.unahur.obj2.profugos.IProfugo;

public interface IZona {
    void agregarProfugo(IProfugo profugo);

    void eliminarProfugo(IProfugo profugo);

    void limpiarProfugos();

    List<IProfugo> getProfugos();
}
