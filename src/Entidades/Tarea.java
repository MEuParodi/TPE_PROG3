package Entidades;

public class Tarea {
    private String id_tarea;
    private String nombre;
    private int tpo_ejecucion;
    private boolean es_critica;
    private int prioridad;

    public Tarea(String id_tarea, String nombre, int tpo_ejecucion, boolean es_critica, int prioridad) {
        this.id_tarea = id_tarea;
        this.nombre = nombre;
        this.tpo_ejecucion = tpo_ejecucion;
        this.es_critica = es_critica;
        this.prioridad = prioridad;
    }

    public String getId_tarea() {
        return id_tarea;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTpo_ejecucion() {
        return tpo_ejecucion;
    }

    public boolean Es_critica() {
        return es_critica;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setId_tarea(String id_tarea) {  // ver si va o no
        this.id_tarea = id_tarea;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTpo_ejecucion(int tpo_ejecucion) {
        this.tpo_ejecucion = tpo_ejecucion;
    }

    public void setEs_critica(boolean es_critica) {
        this.es_critica = es_critica;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    @Override
    public String toString() {
        return id_tarea;
    }
}
