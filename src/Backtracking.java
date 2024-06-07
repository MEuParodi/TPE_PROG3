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
    private Integer mejorTiempo;
    private int contadorEstados;

    public Backtracking(String pathProcesadores, String pathTareas, Integer x){
        this.contadorEstados=0;
        this.mejorTiempo=Integer.MAX_VALUE;
        this.mejorAsignacion=new HashMap<>();
        CSVReader reader = new CSVReader();
        HashMap<String, Procesador> procesadoresReader = reader.readProcessors(pathProcesadores);
        HashMap<String, Tarea> tareasReader = reader.readTasks(pathTareas);
        this.tareas = new ArrayList<>(tareasReader.values());
        this.procesadores = new ArrayList<>(procesadoresReader.values());
        for (Procesador p :procesadores) {
            if (!p.isRefrigerado()) {
                p.setXParaNoRefrigerado(x);
            }
        }
    }
    /*Recorremos los procesadores, y si es posible agregarles la tarea teniendo en cuenta las restricciones de la consigna,
    * la agregamos al procesador en la asignacion parcial, obtenemos el nuevo mejor tiempo parcial que es el maximo entre
    * el mayor tiempo de los procesadores de la asigancion actual y el del procesador actual.
    * Implementamos una poda, solo se realiza el llamado recursivo a backtrack si el tiempo parcial es menor al
    * mejor tiempo encontrado.  */

    public HashMap<Procesador, List<Tarea>> buscarMejorSolucion(){
        HashMap<Procesador, List<Tarea>> asignacionParcial= new HashMap<>();
        Integer mejorTiempoParcial = 0; //mejor tiempo entre todos los p con las tareas asignadas hasta el momento
        backtrack(asignacionParcial, mejorTiempoParcial, 0);
        return  this.mejorAsignacion;
    }

    private void backtrack(HashMap<Procesador,List<Tarea>> asignacionParcial, Integer mejorTiempoParcial, Integer index){
        //si llegue al final
        if(index==(tareas.size()) || (todosLosProcesadoresTienen2Criticas() )){  //REVISAR ESTE OR
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
            Tarea tareaActual = this.tareas.get(index);
            for (Procesador p : procesadores){
                if( p.puedoAgregarTarea(tareaActual)) {
                   asignacionParcial.putIfAbsent(p, new ArrayList<>());// Inicializar la lista de tareas para el procesador si no existe
                    p.agregarTarea(tareaActual);
                   asignacionParcial.get(p).add(tareaActual);// Agregar la tarea actual a la lista de tareas del procesador

                    int tiempoProcesadorActual = p.getTiempoTotal();

                    int nuevoMejorTiempoParcial = Math.max(mejorTiempoParcial, tiempoProcesadorActual);// Actualiza el mejor tiempo parcial
                   //backtrack
                    contadorEstados++;

                    if(nuevoMejorTiempoParcial<mejorTiempo) {//posible poda
                        backtrack(asignacionParcial, nuevoMejorTiempoParcial, index + 1);
                    }
                   //deshacer cambios
                   asignacionParcial.get(p).remove(tareaActual);
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
        System.out.println("Cantidad de estados generados: " + contadorEstados);
    }
}
