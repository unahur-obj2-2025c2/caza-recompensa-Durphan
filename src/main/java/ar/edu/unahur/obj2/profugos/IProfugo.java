package ar.edu.unahur.obj2.profugos;

public interface IProfugo {

    int getInocencia();

    void reducirInocencia(int reduccion);

    void aumentarInocencia(int aumento);

    int getHabilidad();

    void disminuirHabilidad(int disminucion);

    void aumentarHabilidad(int aumento);

    boolean estaNervioso();

    void ponerNervioso();

    void calmar();
}
