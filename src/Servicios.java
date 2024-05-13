import Entidades.Procesador;
import Entidades.Tarea;
import utils.CSVReader;

import java.util.HashMap;
import java.util.List;

/**
 * NO modificar la interfaz de esta clase ni sus métodos públicos.
 * Sólo se podrá adaptar el nombre de la clase "Tarea" según sus decisiones
 * de implementación.
 */
public class Servicios {
    private HashMap<String,Tarea> tareas;
    private HashMap<String, Procesador> procesadores;  //ver si lo vamos a usar

    /*
     * Expresar la complejidad temporal del constructor.
     */
    public Servicios(String pathProcesadores, String pathTareas)
    {
        CSVReader reader = new CSVReader();
       // HashMap<String,Procesador> procesadoresReader = reader.readProcessors(pathProcesadores);
        HashMap<String,Tarea> tareasReader = reader.readTasks(pathTareas);
        this.tareas = new HashMap<String,Tarea>(tareasReader);
        //this.procesadores = new HashMap<String,Procesador>(procesadoresReader);
    }

    /*
     * Expresar la complejidad temporal del servicio 1.
     */
    public Tarea servicio1(String ID) {
        return null;
    }

    /*
     * Expresar la complejidad temporal del servicio 2.
     */
    public List<Tarea> servicio2(boolean esCritica) {
        return null;
    }

    /*
     * Expresar la complejidad temporal del servicio 3.
     */
    public List<Tarea> servicio3(int prioridadInferior, int prioridadSuperior) {
        return null;
    }

    public void imprimir_tareas() {
        System.out.println(tareas);
    }

}