import Entidades.Procesador;
import Entidades.Tarea;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Greedy {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private HashMap<Procesador, List<Tarea>> mejorAsignacion;
    private Integer mejorTiempo;
    private int contadorCandidatos;

    public Greedy(String pathProcesadores, String pathTareas, Integer x) {
        this.contadorCandidatos = 0;
        this.mejorTiempo = 0;
        this.mejorAsignacion = new HashMap<>();
        CSVReader reader = new CSVReader();
        HashMap<String, Procesador> procesadoresReader = reader.readProcessors(pathProcesadores);
        HashMap<String, Tarea> tareasReader = reader.readTasks(pathTareas);
        this.tareas = new ArrayList<>(tareasReader.values());
        this.procesadores = new ArrayList<>(procesadoresReader.values());
        for (Procesador p : procesadores) {
            if (!p.isRefrigerado()) {
                p.setXParaNoRefrigerado(x);
            }
        }
    }

    /*Ordenamos las tareas de mayor a menor por tiempo de ejecución, y las asignamos al procesador que tiene el
     * menor tiempo de procesamiento y puede agregar esa tarea. */
    public void buscarMejorSolucion() {
        int contadorTareasAsignadas = 0;
        // Inicializar la asignación de tareas a procesadores
        for (Procesador p : procesadores) {
            mejorAsignacion.put(p, new ArrayList<>());
        }
        // Ordenar las tareas de mayor a menor tiempo de ejecución
        tareas.sort((t1, t2) -> t2.getTpo_ejecucion() - t1.getTpo_ejecucion());//calcula diferencia de tiempo si es positivo t2 es mayor

        for (Tarea tarea : tareas) {
            Procesador p = this.buscarProcesadorConMenorTiempoYDisponible(tarea);
            if (p.puedoAgregarTarea(tarea)) {
                p.agregarTarea(tarea);
                mejorAsignacion.get(p).add(tarea);
                contadorTareasAsignadas++;
            }
        }
        // Calcular el mejor tiempo
        this.calcularMejorTiempo();

        if (contadorTareasAsignadas == this.tareas.size()) {
            this.imprimirSolucion();
        } else {
            this.imprimirMensajeError();
        }
    }

    public void calcularMejorTiempo() {
        for (Procesador p : procesadores) {
            int tiempoProcesador = p.getTiempoTotal();
            if (tiempoProcesador > this.mejorTiempo) {
                this.mejorTiempo = tiempoProcesador;
            }
        }
    }

    public Procesador buscarProcesadorConMenorTiempoYDisponible(Tarea t) {
        int menorTiempo = Integer.MAX_VALUE;
        Procesador procesadorConMenorTiempo = null;
        for (Procesador p : procesadores) {
            if (p.getTiempoTotal() < menorTiempo && p.puedoAgregarTarea(t)) {
                menorTiempo = p.getTiempoTotal();
                procesadorConMenorTiempo = p;
                contadorCandidatos++;
            }
        }
        return procesadorConMenorTiempo;
    }

    private void imprimirSolucion() {
        System.out.println("Mínimo Tiempo Máximo: " + mejorTiempo);
        System.out.println("Mejor Asignación: " + mejorAsignacion);
        System.out.println("Cantidad de candidatos considerados: " + contadorCandidatos);
    }

    private void imprimirMensajeError() {
        System.out.println("No existe asignación posible");
    }
}
