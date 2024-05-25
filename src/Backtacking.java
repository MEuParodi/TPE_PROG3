import Entidades.Procesador;
import Entidades.Tarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Backtacking {
    private List<Tarea>tareas;
    private List<Procesador> procesadores;
    private HashMap<Procesador, List<Tarea>> mejorAsignacion;
   // private List<Integer>tiemposPorProcesador;
    private Integer mejorTiempo;

    public Backtacking(List<Tarea>tareas, List<Procesador>procesadores){
        this.mejorTiempo=Integer.MAX_VALUE;
        this.mejorAsignacion=new HashMap<>();
        this.tareas=tareas;
        this.procesadores=procesadores;
    }

    public HashMap<Procesador, List<Tarea>> buscarMejorSolucion(){
        HashMap<Procesador, List<Tarea>> asignacionParcial= new HashMap<>();
        Integer mejorTiempoParcial=0;
        backtrack(asignacionParcial, mejorTiempoParcial, 0);
        return  this.mejorAsignacion;
    }
    private void backtrack(HashMap<Procesador,List<Tarea>> asignacionParcial, Integer mejorTiempoParcial, Integer index){
        //si llegue al final
        //si es tareas.size()-1 no estaria asignando la ultima tarea
        if(index==tareas.size()){
            if (mejorTiempoParcial<this.mejorTiempo){
                this.mejorTiempo=mejorTiempoParcial;
                this.mejorAsignacion=new HashMap<>(asignacionParcial);
            }
        }
        else {
            Tarea tareaActual=this.tareas.get(index);
            for (Procesador p : procesadores){
                p.agregarTarea(tareaActual);

                // Inicializar la lista de tareas para el procesador si no existe
                asignacionParcial.putIfAbsent(p, new ArrayList<>());
                // Agregar la tarea actual a la lista de tareas del procesador
                asignacionParcial.get(p).add(tareaActual);


            }
        }
    }


}
