package ar.edu.unahur.obj2.profugos.entrenamientos;

import ar.edu.unahur.obj2.profugos.IProfugo;

public class Elite extends BaseEntrenamiento {

    public Elite(IProfugo base) {
        super(base);
        if (base instanceof Elite) {
            throw new IllegalArgumentException("Un profugo elite no puede entrenar otra vez como elite");
        }
        if (base.estaNervioso()) {
            base.calmar();
        }
    }

    @Override
    public void ponerNervioso() {
        throw new IllegalStateException("Un profugo elite no puede ponerse nervioso");
    }

    @Override
    public void calmar() {
        // no hace nada, siempre esta calmado
    }

}
