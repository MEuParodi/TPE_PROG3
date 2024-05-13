public class Main {

    public static void main(String args[]) {
        Servicios servicios = new Servicios("./src/datasets/Procesadores.csv", "./src/datasets/Tareas.csv");
        servicios.imprimir_tareas();
        System.out.println(servicios.servicio1("T4"));
        System.out.println("Tareas críticas: \n" + servicios.servicio2(true));
        System.out.println("Tareas no críticas: \n" + servicios.servicio2(false));
        System.out.println("Tareas con prioridad entre 70 y 100: \n" + servicios.servicio3(70,100));
    }

}