package nomina;

public class Menu {

    public static void menuPrincipal() {
        System.out.println("----- Menu de opciones -----");
        System.out.println("1. Registrar nuevo empleado.");
        System.out.println("2. Dar de baja a un empleado.");
        System.out.println("3. Actualizar empleado.");
        System.out.println("4. Registrar dias trabajados.");
        System.out.println("5. Registrar horas extra.");
        System.out.println("6. Registrar un nuevo puesto.");
        System.out.println("7. Calcular nomina.");
        System.out.println("8. Ver historial de nomina.");
        System.out.println("9. Salir.");
    }

    public static void menuDeOpciones() {
        System.out.println("----- Opciones -----");
        System.out.println("1. De un empleado.");
        System.out.println("2. De toda la empresa.");
        System.out.println("3. Regresar al menu anterior.");
    }

    public static void menuActualizacionDeDatos() {
        System.out.println("----- Opciones -----");
        System.out.println("1. Nombre.");
        System.out.println("2. Apellido.");
        System.out.println("3. Edad.");
        System.out.println("4. Regresar al menu anterior.");
    }
}
