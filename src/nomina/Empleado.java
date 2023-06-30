package nomina;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Empleado extends Persona {
    private int diasDeTrabajo;
    private int horasExtra;
    private Puesto puesto;
    private static List<Empleado> registroDeEmpleados;

    private double salarioBase;
    private double salarioHorasExtra;


    // Getters y Setters

    public int getDiasDeTrabajo() {
        return diasDeTrabajo;
    }

    public void setDiasDeTrabajo(int diasDeTrabajo) {
        this.diasDeTrabajo = diasDeTrabajo;
    }

    public int getHorasExtra() {
        return horasExtra;
    }

    public void setHorasExtra(int horasExtra) {
        this.horasExtra = horasExtra;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
    }

    public static List<Empleado> getRegistroDeEmpleados() {
        return registroDeEmpleados;
    }

    public static void setRegistroDeEmpleados(List<Empleado> registroDeEmpleados) {
        Empleado.registroDeEmpleados = registroDeEmpleados;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public void setSalarioBase(double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public double getSalarioHorasExtra() {
        return salarioHorasExtra;
    }

    public void setSalarioHorasExtra(double salarioHorasExtra) {
        this.salarioHorasExtra = salarioHorasExtra;
    }

    // Constructor predeterminado
    public Empleado() {
        super();
        this.diasDeTrabajo = 0;
        this.horasExtra = 0;
        this.puesto = null;
    }

    public Empleado(int id, String nombre, String apellido, String domicilio, int edad,
                    int diasDeTrabajo, int horasExtra, Puesto puesto) {
        super(id, nombre, apellido, domicilio, edad);
        this.diasDeTrabajo = diasDeTrabajo;
        this.horasExtra = horasExtra;
        this.puesto = puesto;
    }

    // Metodos
    public static void inicializarRegistroDeEmpleados() {
        registroDeEmpleados = new ArrayList<>();
    }

    public static void registrarEmpleado(Empleado empleado) {
        registroDeEmpleados.add(empleado);
    }

    public void bajaDeEmpleado(Empleado empleado) {
        registroDeEmpleados.remove(empleado);
    }

    public static void actualizarDatos(Empleado empleado) {
        int opcion;
        do {
            Menu.menuActualizacionDeDatos();
            opcion = new Scanner(System.in).nextInt();

            switch (opcion) {
                case 1 -> {
                    // Actualizar nombre
                    System.out.println("Ingrese el nuevo nombre: ");
                    empleado.setNombre(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion exitosa. Nombre: " +
                            empleado.getNombre());
                }
                case 2 -> {
                    // Actualizar apellido
                    System.out.println("Ingrese el nuevo apellido: ");
                    empleado.setApellido(new Scanner(System.in).nextLine());
                    System.out.println("Actualizacion exitosa. Apellido: " +
                            empleado.getApellido());
                }
                case 3 -> {
                    // Actualizar edad
                    System.out.println("Ingrese la nueva edad: ");
                    empleado.setEdad(new Scanner(System.in).nextInt());
                    System.out.println("Actualizacion exitosa. Edad: " +
                            empleado.getEdad());
                }
                default -> {
                }
            }
        } while (opcion != 4);
    }

    public void registrarDiasLaboradosEmpleado() {
        int dias;
        do {
            dias = new Scanner(System.in).nextInt();

            if (dias > 0 && dias <= 20) {
                this.setDiasDeTrabajo(dias);
                System.out.println("¡Registro exitoso!");
            } else if (dias > 20) {
                System.out.println("Alerta: la jornada laboral no puede exceder los 20 días al mes.");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida.");
            }
        } while (dias > 20 || dias < 0);
    }

    public static void registrarDiasLaborados() {
        int dias;
        do {
            dias = new Scanner(System.in).nextInt();

            if (dias > 0 && dias <= 20) {
                for (Empleado empleado : registroDeEmpleados) {
                    empleado.setDiasDeTrabajo(dias);
                }
                System.out.println("¡Registro exitoso!");
            } else if (dias > 20){
                System.out.println("Alerta: la jornada laboral no puede exceder los 20 días al mes.");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida.");
            }
        } while (dias > 20 || dias < 0);
    }

    public void registrarHorasExtraEmpleado() {
        int horas;
        do {
            horas = new Scanner(System.in).nextInt();

            if (horas > 1 && horas <= 40) {
                this.setHorasExtra(horas);
                System.out.println("¡Registro exitoso!");
            } else if (horas > 40) {
                System.out.println("Alerta: no se permite mas de 40 horas extra al mes.");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida.");
            }
        } while (horas < 1 || horas > 40);
    }

    public static void registrarHorasExtra() {
        int horas;
        do {
            horas = new Scanner(System.in).nextInt();

            if (horas > 1 && horas <= 40) {
                for (Empleado empleado : registroDeEmpleados) {
                    empleado.setHorasExtra(horas);
                    System.out.println("¡Registro exitoso!");
                }
            } else if (horas > 40) {
                System.out.println("Alerta: no se permite mas de 40 horas extra al mes.");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida.");
            }
        } while (horas < 1 || horas > 40);
    }

    public static Empleado buscarEmpleadoPorID(int id) {
        for (Empleado empleado : registroDeEmpleados) {
            if (empleado.getId() == id) {
                return empleado;
            }
        }
        return null;
    }

    public static Empleado checarExistenciaEmpleado() {
        Empleado empleado;
        do {
            empleado = buscarEmpleadoPorID(new Scanner(System.in).nextInt());
            if (empleado == null) {
                System.out.println("Empleado no encontrado. Por favor, ingrese un ID valido.");
            }
        } while (empleado == null);

        return empleado;
    }

    public static int checarDias() {
        int dias;
        do {
            dias = new Scanner(System.in).nextInt();
            if (dias > 20 || dias < 0) {
                System.out.println("Por favor, ingrese una cantidad valida. La jornada laboral" +
                        " no puede exceder los 20 dias mensuales.");
            } else {
                return dias;
            }
        } while (dias > 20 || dias < 0);
        return 0;
    }

    public static int checarHoras() {
        int horas;
        do {
            horas = new Scanner(System.in).nextInt();
            if (horas > 40 || horas < 1) {
                System.out.println("Por favor, ingrese una cantidad valida. No se puede superar " +
                        "las 40 horas extra mensuales.");
            } else {
                return horas;
            }
        } while (horas > 40 || horas < 1);
        return 0;
    }

}
