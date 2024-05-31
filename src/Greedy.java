import Entidades.Procesador;
import Entidades.Tarea;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Greedy {
    private List<Tarea> tareas;
    private List<Procesador> procesadores;
    private HashMap<Procesador, List<Tarea>> mejorAsignacion;
    private Integer mejorTiempo;
    private int contadorCandidatos;

    public Greedy(String pathProcesadores, String pathTareas, Integer x){
        this.contadorCandidatos = 0;
        this.mejorTiempo = 0;
        this.mejorAsignacion = new HashMap<>();
        CSVReader reader = new CSVReader();
        HashMap<String, Procesador> procesadoresReader = reader.readProcessors(pathProcesadores);
        HashMap<String, Tarea> tareasReader = reader.readTasks(pathTareas);
        this.tareas = new ArrayList<>(tareasReader.values());
        for (Tarea t : tareas)
            System.out.println(t.toString());
        this.procesadores = new ArrayList<>(procesadoresReader.values());
        for (Procesador p : procesadores) {
            if (!p.isRefrigerado()) {
                p.setXParaNoRefrigerado(x);
            }
        }
    }

    public HashMap<Procesador, List<Tarea>> buscarMejorSolucion() {
        // Inicializar la asignación de tareas a procesadores
        for (Procesador p : procesadores) {
            mejorAsignacion.put(p, new ArrayList<>());
        }

        // Ordenar las tareas de mayor a menor tiempo de ejecución
        tareas.sort((t1, t2) -> t2.getTpo_ejecucion() - t1.getTpo_ejecucion());//calcula diferencia de tiempo si es positivo t2 es mayor

        // Asignar cada tarea al procesador con el menor tiempo acumulado
        for (Tarea tarea : tareas) {
            Procesador mejorProcesador = null;
            int tiempoMinimo = Integer.MAX_VALUE;

            for (Procesador p : procesadores) {
                contadorCandidatos++;
                int tiempoTotal = p.getTiempoTotal();
                if (tiempoTotal < tiempoMinimo) {
                    mejorProcesador = p;
                    tiempoMinimo = tiempoTotal;
                }
            }

            mejorProcesador.agregarTarea(tarea);
            mejorAsignacion.get(mejorProcesador).add(tarea);
        }

        // Calcular el mejor tiempo
        for (Procesador p : procesadores) {
            int tiempoProcesador = p.getTiempoTotal();
            if (tiempoProcesador > mejorTiempo) {
                mejorTiempo = tiempoProcesador;
            }
        }

        return mejorAsignacion;
    }

    public void imprimirSolucion() {
        System.out.println("Mínimo Tiempo Máximo: " + mejorTiempo);
        System.out.println("Mejor Asignación: " + mejorAsignacion);
        System.out.println("Cantidad de candidatos considerados: " + contadorCandidatos);
    }
}
