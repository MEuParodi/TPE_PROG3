public class Main {

    public static void main(String args[]) {
        String pathProcesadores = "./src/datasets/Procesadores.csv";
        String pathTareas = "./src/datasets/Tareas.csv";
        Servicios servicios = new Servicios(pathProcesadores,pathTareas );
     //   servicios.imprimir_tareas();
     //   System.out.println(servicios.servicio1("T4"));
     //   System.out.println("Tareas críticas: \n" + servicios.servicio2(true));
     //   System.out.println("Tareas no críticas: \n" + servicios.servicio2(false));
     //   System.out.println("Tareas con prioridad entre 70 y 100: \n" + servicios.servicio3(70,100));

        Backtracking backTracking = new Backtracking(pathProcesadores,pathTareas, 2000);
        backTracking.buscarMejorSolucion();
        backTracking.imprimirSolucion();


    }

}