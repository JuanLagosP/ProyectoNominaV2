package nomina;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    public static void verRegistroDeEmpleados() {
        for (Empleado e : registroDeEmpleados) {
            System.out.printf("%s%n %s%n %s%n %s%n %s%n",
                    "ID: " + e.getId(),
                    "Nombre: " + e.getNombre(),
                    "Apellido: " + e.getApellido(),
                    "Edad: " + e.getEdad(),
                    "Puesto: " + e.getPuesto().getNombrePuesto() + "\n");
        }
    }

    public void bajaDeEmpleado(Empleado empleado) {
        registroDeEmpleados.remove(empleado);
    }

    public static void actualizarDatos(Empleado empleado) {
        int opcion;
        do {
            new Menu().menuActualizacionDeDatos();
            try {
                System.out.println("Ingrese la opcion deseada: ");
                opcion = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Solo se permiten caracteres numericos.");
                new Scanner(System.in).nextLine();
                continue;
            }

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
                    do {
                        try {
                            System.out.println("Ingrese la nueva edad: ");
                            empleado.setEdad(new Scanner(System.in).nextInt());
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Error: solo se permiten caracteres numericos." +
                                    "Por favor, intente de nuevo.");
                        }
                    } while (true);

                    System.out.println("Actualizacion exitosa. Edad: " +
                            empleado.getEdad());
                }
                default -> {}
            }
            if (opcion == 4) {
                break;
            }
        } while (true);
    }

    public void registrarDiasLaboradosEmpleado() {
        int dias;
        do {
            try {
                System.out.println("Dias laborados: ");
                dias = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }

            if (dias > 0 && dias <= 20) {
                this.setDiasDeTrabajo(dias);
                System.out.println("¡Registro exitoso!");
                break;
            } else if (dias > 20) {
                System.out.println("Alerta: la jornada laboral no puede exceder los 20 días al mes.");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida.");
            }
        } while (true);
    }

    public static void registrarDiasLaborados() {
        int dias;
        do {
            try {
                System.out.println("Dias laborados: ");
                dias = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }

            if (dias > 0 && dias <= 20) {
                for (Empleado empleado : registroDeEmpleados) {
                    empleado.setDiasDeTrabajo(dias);
                }
                System.out.println("¡Registro exitoso!");
                break;
            } else if (dias > 20){
                System.out.println("Alerta: la jornada laboral no puede exceder los 20 días al mes.");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida.");
            }
        } while (true);
    }

    public void registrarHorasExtraEmpleado() {
        int horas;
        do {
            try {
                System.out.println("Horas extra: ");
                horas = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }

            if (horas > 1 && horas <= 40) {
                this.setHorasExtra(horas);
                System.out.println("¡Registro exitoso!");
                break;
            } else if (horas > 40) {
                System.out.println("Alerta: no se permiten mas de 40 horas extra al mes.");
                System.out.println("Por favor, ingrese una cantidad valida: ");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida:");
            }
        } while (true);
    }

    public static void registrarHorasExtra() {
        int horas;
        do {
            try {
                System.out.println("Horas extra: ");
                horas = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }

            if (horas > 1 && horas <= 40) {
                for (Empleado empleado : registroDeEmpleados) {
                    empleado.setHorasExtra(horas);
                }
                System.out.println("¡Registro exitoso!");
                break;
            } else if (horas > 40) {
                System.out.println("Alerta: no se permite mas de 40 horas extra al mes.");
                System.out.println("Por favor, ingrese una cantidad valida: ");
            } else {
                System.out.println("Por favor, ingrese una cantidad valida: ");
            }
        } while (true);
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
            try {
                empleado = buscarEmpleadoPorID(new Scanner(System.in).nextInt());
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }
            if (empleado == null) {
                System.out.println("Empleado no encontrado. Por favor, ingrese un ID valido: ");
            } else {
                break;
            }
        } while (true);

        return empleado;
    }

    public static int checarDias() {
        int dias;
        do {
            try {
                System.out.println("Dias de trabajo:");
                dias = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }
            if (dias > 20 || dias < 0) {
                System.out.println("Por favor, ingrese una cantidad valida. La jornada laboral" +
                        " no puede exceder los 20 dias mensuales.");
            } else {
                return dias;
            }
        } while (true);
    }

    public static int checarHoras() {
        int horas;
        do {
            try {
                System.out.println("Horas extra: ");
                horas = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                continue;
            }

            if (horas > 40 || horas < 1) {
                System.out.println("Por favor, ingrese una cantidad valida. No se puede superar " +
                        "las 40 horas extra mensuales.");
            } else {
                return horas;
            }
        } while (true);
    }

}
