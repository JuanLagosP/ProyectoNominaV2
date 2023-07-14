package nomina;

public class Menu {

    public void menuPrincipal() {
        System.out.println("----- Menu de opciones -----");
        System.out.println("1. Registro de empleados.");
        System.out.println("2. Dar de baja a un empleado.");
        System.out.println("3. Actualizar empleado.");
        System.out.println("4. Registrar dias trabajados.");
        System.out.println("5. Registrar horas extra.");
        System.out.println("6. Registro de puestos.");
        System.out.println("7. Calcular nomina.");
        System.out.println("8. Ver historial de nomina.");
        System.out.println("9. Salir.");
    }

    public void menuDeOpciones() {
        System.out.println("----- Opciones -----");
        System.out.println("1. De un empleado.");
        System.out.println("2. De toda la empresa.");
        System.out.println("3. Regresar al menu anterior.");
    }

    public void menuActualizacionDeDatos() {
        System.out.println("----- Opciones -----");
        System.out.println("1. Nombre.");
        System.out.println("2. Apellido.");
        System.out.println("3. Edad.");
        System.out.println("4. Regresar al menu anterior.");
    }

    public void menuRegistro() {
        System.out.println("----- Opciones -----");
        System.out.println("1. Ver registro");
        System.out.println("2. Nuevo registro");
        System.out.println("3. Regresar al menu anterior");
    }

    public void menuHistorial() {
        System.out.println("----- Opciones -----");
        System.out.println("1. Mostrar en orden cronologico");
        System.out.println("2. Mostrar en orden alfabetico");
        System.out.println("3. Mostrar de menor a mayor facturacion");
        System.out.println("4. Mostrar de mayor a menor facturacion");
        System.out.println("5. Regresar al menu anterior.");
    }

    public Menu() {

    }
}
