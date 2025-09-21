package ar.edu.unahur.obj2.profugos;

public class Profugo implements IProfugo {
    private int inocencia = 100;
    private int habilidad;
    private boolean estaNervioso;

    public int getInocencia() {
        return inocencia;
    }

    public Profugo(boolean estaNervioso, int habilidad) {
        this.estaNervioso = estaNervioso;
        if (habilidad < 0) {
            throw new IllegalArgumentException("La habilidad no puede ser menor a 0");
        }
        if (habilidad > 100) {
            throw new IllegalArgumentException("La habilidad no puede ser mayor a 100");
        }
        this.habilidad = habilidad;
    }

    public void reducirInocencia(int reduccion) {
        if (this.inocencia - reduccion < 0) {
            this.inocencia = 0;
            return;
        }
        this.inocencia -= reduccion;
    }

    public void aumentarInocencia(int aumento) {
        this.inocencia += aumento;
    }

    public int getHabilidad() {
        return habilidad;
    }

    public void disminuirHabilidad(int disminucion) {
        if (this.habilidad - disminucion < 0) {
            this.habilidad = 0;
            return;
        }
        this.habilidad -= disminucion;
    }

    public void aumentarHabilidad(int aumento) {
        if (this.habilidad + aumento > 100) {
            this.habilidad = 100;
            return;
        }
        this.habilidad += aumento;
    }

    public boolean estaNervioso() {
        return estaNervioso;
    }

    public void ponerNervioso() {
        estaNervioso = true;
    }

    public void calmar() {
        estaNervioso = false;
    }

}
