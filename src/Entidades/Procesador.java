package Entidades;

import java.util.ArrayList;
import java.util.List;

public class Procesador {
    private String id_procesador;
    private String codigo;
    private boolean refrigerado;
    private int anio;
    private List<Tarea>tareas;
    private Integer tiempoTotal;
    private Integer cantCriticas;
    private Integer x; //tiempo limite para no refrig


    public Procesador(String id_procesador, String codigo, boolean refrigerado, int anio) {
        this.id_procesador = id_procesador;
        this.codigo = codigo;
        this.refrigerado = refrigerado;
        this.anio = anio;
        this.tareas=new ArrayList<>();
        this.cantCriticas = 0;
        this.tiempoTotal = 0;
    }

    public Integer getTiempoTotal() {
        return tiempoTotal;
    }

    public List<Tarea> getTareas() {
        return new ArrayList<>(this.tareas);
    }

    public void agregarTarea(Tarea t){
        if(this.puedoAgregarTarea(t)){
            this.tareas.add(t);
            if(t.Es_critica()){
                cantCriticas++;
            }
            this.tiempoTotal+=t.getTpo_ejecucion();
        }
    }
    public boolean puedoAgregarTarea(Tarea t){
        if(t.Es_critica() && this.getCantDeCriticas()>=2) {
            return false;
        }
        if(!this.refrigerado) {
            if (tiempoTotal+t.getTpo_ejecucion()>x){//<=
                return false;
            }
        }
        return true;

    }

    public void quitarTarea(Tarea t){
        this.tareas.remove(t);
        this.tiempoTotal-=t.getTpo_ejecucion();
        if(t.Es_critica()){
            cantCriticas --;
        }
    }

    public void setXParaNoRefrigerado(Integer x){
        this.x=x;
    }
    public boolean puedoAgregarPorTiempo(int tiempoTarea){
            if (this.tiempoTotal+tiempoTarea>this.x){
                return false;
            }

        return true;
    }

    public Integer getCantDeCriticas(){
        return this.cantCriticas;
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

    @Override
    public String toString() {
        return id_procesador;
    }
}
