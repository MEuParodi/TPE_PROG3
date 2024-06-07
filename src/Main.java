
public class Main {

    public static void main(String args[]) {
        String pathProcesadores = "./src/datasets/Procesadores.csv";
        String pathTareas = "./src/datasets/Tareas.csv";

        System.out.println("Parte 1 ------------------");
        Servicios servicios = new Servicios(pathProcesadores, pathTareas);
        System.out.println(servicios.servicio1("T4"));
        System.out.println("Tareas críticas: \n" + servicios.servicio2(true));
        System.out.println("Tareas no críticas: \n" + servicios.servicio2(false));
        System.out.println("Tareas con prioridad entre 70 y 100: \n" + servicios.servicio3(70, 100));


        System.out.println("Backtracking------------------");
        Backtracking backTracking = new Backtracking(pathProcesadores, pathTareas, 2000);
        backTracking.buscarMejorSolucion();
        backTracking.imprimirSolucion();

        System.out.println("Greedy------------------------");
        Greedy greedy = new Greedy(pathProcesadores, pathTareas, 2000);
        greedy.buscarMejorSolucion();

    }

}