package ar.edu.unahur.obj2.profugos.entrenamientos;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class ArtesMarciales extends BaseEntrenamiento {

    public ArtesMarciales(IProfugo base) {
        super(base);
        if (base instanceof ArtesMarciales) {
            throw new IllegalArgumentException("El profugo ya entrena artes marciales");
        }
        base.aumentarHabilidad(getHabilidad());
    }

}
