import Entidades.Procesador;
import Entidades.Tarea;
import utils.CSVReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.*;
public class Backtracking {
    private List<Tarea>tareas;
    private List<Procesador> procesadores;
    private HashMap<Procesador, List<Tarea>> mejorAsignacion;
   // private List<Integer>tiemposPorProcesador;
    private Integer mejorTiempo;

    public Backtracking(String pathProcesadores, String pathTareas, Integer x){
        this.mejorTiempo=Integer.MAX_VALUE;
        this.mejorAsignacion=new HashMap<>();
        CSVReader reader = new CSVReader();
        HashMap<String, Procesador> procesadoresReader = reader.readProcessors(pathProcesadores);
        HashMap<String, Tarea> tareasReader = reader.readTasks(pathTareas);
        this.tareas = new ArrayList<>(tareasReader.values());
        for (Tarea t : tareas)
            System.out.println(t.toString());
        this.procesadores = new ArrayList<>(procesadoresReader.values());
        for (Procesador p :procesadores) {
            if (!p.isRefrigerado()) {
                p.setXParaNoRefrigerado(x);
            }
            //    System.out.println(p.toString());
        }
    }

    public HashMap<Procesador, List<Tarea>> buscarMejorSolucion(){
        HashMap<Procesador, List<Tarea>> asignacionParcial= new HashMap<>();
        Integer mejorTiempoParcial = 0; //mejor tiempo entre todos los p con las tareas asignadas hasta el momento
        backtrack(asignacionParcial, mejorTiempoParcial, 0);
        return  this.mejorAsignacion;
    }

    private void backtrack(HashMap<Procesador,List<Tarea>> asignacionParcial, Integer mejorTiempoParcial, Integer index){
        //si llegue al final
        if(index==(tareas.size()) || (todosLosProcesadoresTienen2Criticas() )){
            System.out.println(" entre al if de corte ## Mejor Tiempo Parcial: " + mejorTiempoParcial + "----" );
            if (mejorTiempoParcial < this.mejorTiempo){
                this.mejorTiempo = mejorTiempoParcial;
                this.mejorAsignacion = new HashMap<>(asignacionParcial);

                for (Map.Entry<Procesador, List<Tarea>> entry : asignacionParcial.entrySet()) {
                    Procesador procesador = entry.getKey();
                    List<Tarea> tareas = new ArrayList<>(entry.getValue());
                    this.mejorAsignacion.put(procesador, tareas);
                }

            }
        }

        else {
            System.out.println("entre en el else de backtrack");
            Tarea tareaActual = this.tareas.get(index);
            for (Procesador p : procesadores){
                if( p.agregarTarea(tareaActual)) {

                   asignacionParcial.putIfAbsent(p, new ArrayList<>());// Inicializar la lista de tareas para el procesador si no existe

                   asignacionParcial.get(p).add(tareaActual);// Agregar la tarea actual a la lista de tareas del procesador
                    System.out.println("Asignacion Parcial: " + asignacionParcial);

                   //mejorTiempoParcial += tareaActual.getTpo_ejecucion();

                    int tiempoProcesadorActual = p.getTiempoTotal();


                    int nuevoMejorTiempoParcial = Math.max(mejorTiempoParcial, tiempoProcesadorActual);// Actualiza el mejor tiempo parcial
                   //backtrack
                   backtrack(asignacionParcial, nuevoMejorTiempoParcial, index + 1);

                   //deshacer cambios
                   asignacionParcial.get(p).remove(tareaActual);
                   //mejorTiempoParcial -= tareaActual.getTpo_ejecucion();
                   p.quitarTarea(tareaActual);
               }
            }
        }
    }

    private boolean todosLosProcesadoresTienen2Criticas(){
        int cantDeProcesadoresConMasDe2Criticas = 0;
        for (Procesador p : procesadores){
            if (p.getCantDeCriticas() > 2){
                cantDeProcesadoresConMasDe2Criticas ++;
            }
        }
        if (cantDeProcesadoresConMasDe2Criticas == procesadores.size()){
            return true;
        }
        return false;
    }
    public void imprimirSolucion(){
        System.out.println("Mínimo Tiempo Máximo: " + mejorTiempo);
        System.out.println("Mejor Asignación: " + mejorAsignacion);
    }
}
