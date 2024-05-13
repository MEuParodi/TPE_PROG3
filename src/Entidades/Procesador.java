package Entidades;

public class Procesador {
    private String id_procesador;
    private String codigo;
    private boolean refrigerado;
    private int anio;

    public Procesador(String id_procesador, String codigo, boolean refrigerado, int anio) {
        this.id_procesador = id_procesador;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
    }

    public String getId_procesador() {
        return id_procesador;
    }

    public String getCodigo() {
        return codigo;
    }

    public boolean isRefrigerado() {
        return refrigerado;
    }

    public int getAnio() {
        return anio;
    }

    public void setId_procesador(String id_procesador) { //ver si va
        this.id_procesador = id_procesador;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setRefrigerado(boolean refrigerado) {
        this.refrigerado = refrigerado;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }
}
