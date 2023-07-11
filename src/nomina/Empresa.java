package nomina;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Map;
import java.util.LinkedHashMap;

public class Empresa implements Nomina {
    // Atributos
    private double[] nominaEmpresa;
    private Map<String, double[]> historialDeNomina;

    // Getters y Setters
    public double[] getNominaEmpresa() {
        return nominaEmpresa;
    }

    public void setNominaEmpresa(double[] nominaEmpresa) {
        this.nominaEmpresa = nominaEmpresa;
    }

    public Map<String, double[]> getHistorialDeNomina() {
        return historialDeNomina;
    }

    /* public void setHistorialDeNomina(Map<String, double[]> historialDeNomina) {
     *     this.historialDeNomina = historialDeNomina;
     * }
     */

    // Metodos de la Interfaz de Nomina
    @Override
    public void calcularNominaEmpleado() {
        Empleado empleado = Empleado.checarExistenciaEmpleado();

        Puesto puestoEmpleado = empleado.getPuesto();

        empleado.setSalarioBase(empleado.getDiasDeTrabajo() * puestoEmpleado.getSalarioPorDia());
        empleado.setSalarioHorasExtra(empleado.getHorasExtra() * puestoEmpleado.getSalarioPorHoraExtra());

        System.out.println("¡Calculo exitoso!");
        System.out.println("Empleado(a): " + empleado.getNombre() + " " + empleado.getApellido() +" - Salario Base: " +
                empleado.getSalarioBase() + " - Horas Extra: " + empleado.getSalarioHorasExtra());
    }

    @Override
    public void calcularNominaEmpresa() {
        double[] nomina = new double[] {0.0, 0.0, 0.0};

        for (Empleado empleado : Empleado.getRegistroDeEmpleados()) {
            nomina[0] += empleado.getDiasDeTrabajo() * empleado.getPuesto().getSalarioPorDia();
            nomina[1] += empleado.getHorasExtra() * empleado.getPuesto().getSalarioPorHoraExtra();
        }
        nomina[2] = nomina[0] + nomina[1];
        setNominaEmpresa(nomina);
    }

    @Override
    public void inicializarHistorialDeNomina() {
        historialDeNomina = new LinkedHashMap<>();

        String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
        "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
        double[] nominaInicial = new double[] {0.0, 0.0, 0.0};

        for (String mes : meses) {
            historialDeNomina.put(mes, nominaInicial);
        }
    }

    @Override
    public void guardarNomina() {
        System.out.println("¿Desea almacenar la nomina en el historial?  1 (Si) -- 2 (No)");
        int respuesta = new Scanner(System.in).nextInt();

        if (respuesta == 1) {
            System.out.println("¿A que mes corresponde?");
            String mesDeRegistro = new Scanner(System.in).nextLine();

            for (Map.Entry<String, double[]> entradaNomina : getHistorialDeNomina().entrySet()) {
                String mes = entradaNomina.getKey();
                if (mes.equalsIgnoreCase(mesDeRegistro)) {
                    historialDeNomina.put(mes, getNominaEmpresa());
                }
            }
        }
    }

    @Override
    public void verHistorialDeNomina() {
        for (Map.Entry<String, double[]> entradaNomina : getHistorialDeNomina().entrySet()) {
            String mes = entradaNomina.getKey();
            double[] nomina = entradaNomina.getValue();

            System.out.println(mes + " - Salario Total: " + nomina[0] +
                    " - Horas Extra: " + nomina[1] + " - Total: " + nomina[2]);
        }
    }

    public Empresa() {
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Empresa empresa = new Empresa();

        Empleado.inicializarRegistroDeEmpleados();
        Puesto.inicializarRegistroDePuestos();
        empresa.inicializarHistorialDeNomina();

        Puesto gerente = new Puesto(1, "Gerente", 1570.25, 250.75);
        Puesto supervisor = new Puesto(2, "Supervisor", 1345.50, 220.10);
        Puesto tecnicoInformatico = new Puesto(3, "Tecnico informatico", 1240.35, 200.20);

        Puesto.registrarPuesto(gerente);
        Puesto.registrarPuesto(supervisor);
        Puesto.registrarPuesto(tecnicoInformatico);

        Empleado empleado1 = new Empleado(101, "Juan", "Lagos", "Callejon Tetitla", 24, 20, 2, gerente);
        Empleado empleado2 = new Empleado(102, "Madelein", "Diaz", "Sur 10", 25, 20, 5, supervisor);
        Empleado empleado3 = new Empleado(103, "Pablo", "Salmeron", "Av. Universidad 235", 30, 15, 10, tecnicoInformatico);
        Empleado empleado4 = new Empleado(104, "Gabriela", "Fernandez", "Calle Durango 120", 27, 18, 3, tecnicoInformatico);
        Empleado empleado5 = new Empleado(105, "Leonardo", "Rubio", "Av. Aeropuerto 132", 29, 20, 2, supervisor);

        Empleado.registrarEmpleado(empleado1);
        Empleado.registrarEmpleado(empleado2);
        Empleado.registrarEmpleado(empleado3);
        Empleado.registrarEmpleado(empleado4);
        Empleado.registrarEmpleado(empleado5);

        int opcion;

        do {
            Menu.menuPrincipal();
            try {
                opcion = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Opcion invalida. Solo se permiten caracteres numericos.");
                scanner.nextLine();
                continue;
            }

            switch (opcion) {
                case 1 -> {
                    int op;
                    do {
                        Menu.menuRegistro();
                        try {
                            op = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opcion ivalida. Solo se permiten caracteres numericos.");
                            scanner.nextLine();
                            continue;
                        }

                        if (op == 1) {
                            Empleado.verRegistroDeEmpleados();
                        } else if (op == 2) {
                            // Registro de un nuevo empleado
                            System.out.println("Por favor, ingrese la informacion que se solicita.");
                            System.out.println("ID: ");
                            int id;
                            Empleado empleado;
                            // Verificar que el ID ingresado no corresponda al de un empleado ya registrado.
                            do {
                                try {
                                    id = scanner.nextInt();
                                } catch (InputMismatchException e) {
                                    System.out.println("Solo se permiten caracteres numericos.");
                                    scanner.nextLine();
                                    continue;
                                }
                                empleado = Empleado.buscarEmpleadoPorID(id);

                                if (empleado != null) {
                                    System.out.println("El ID ingresado ya pertenece a un usuario. Por favor, ingrese " +
                                            "un nuevo ID.");
                                } else {
                                    break;
                                }
                            } while (true);

                            // Registro de nombre, apellido, domicilio, edad, etc.
                            String[] datos = new String[3];
                            int[] datosNumericos = new int[3];

                            System.out.println("Nombre: ");
                            scanner.nextLine();
                            datos[0] = scanner.nextLine();

                            System.out.println("Apellido: ");
                            datos[1] = scanner.nextLine();

                            System.out.println("Domicilio: ");
                            datos[2] = scanner.nextLine();

                            System.out.println("Edad: ");
                            datosNumericos[0] = scanner.nextInt();

                            System.out.println("Dias de trabajo: ");
                            datosNumericos[1] = Empleado.checarDias();

                            System.out.println("Horas extra: ");
                            datosNumericos[2] = Empleado.checarHoras();

                            System.out.println("Puesto: ");
                            scanner.nextLine();

                            // Verificar que el puesto ingresado exista.
                            Puesto puesto = Puesto.checarExistenciaPuesto();

                            // Agregar el nuevo empleado al registro.
                            Empleado.registrarEmpleado(new Empleado(id, datos[0], datos[1], datos[2],
                                    datosNumericos[0], datosNumericos[1], datosNumericos[2], puesto));

                            System.out.println("¡Registro exitoso!");
                        } else {
                            break;
                        }
                    } while (true);
                }
                case 2 -> {
                    // Dar de baja a un empleado
                    System.out.println("Ingrese el ID del empleado que desea dar de baja: ");

                    // Verificar que el empleado exista en el registro
                    Empleado bajaEmpleado = Empleado.checarExistenciaEmpleado();

                    // Eliminar empleado del registro
                    if (bajaEmpleado != null) {
                        Empleado.getRegistroDeEmpleados().remove(bajaEmpleado);
                        System.out.println("¡Baja exitosa!");
                    }
                }
                case 3 -> {
                    // Actualizar datos de un empleado
                    System.out.println("Por favor, ingrese el ID del empleado: ");

                    // Verificar que el empleado exista en el registro
                    Empleado empleadoActualizado = Empleado.checarExistenciaEmpleado();
                    scanner.nextLine();

                    // Desplegar menu para que el usuario seleccione el dato a actualizar del empleado
                    System.out.println("Seleccione el dato que desea actualizar.");
                    Empleado.actualizarDatos(empleadoActualizado);
                }
                case 4 -> {
                    // Registrar dias de trabajo
                    int op;
                    /*
                     * Desplegar menu para que el usuario seleccione si registrar los dias trabajados de un empleado
                     * o de todos los empleados.
                     */
                    do {
                        Menu.menuDeOpciones();
                        try {
                            op = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opcion invalida. Solo se permiten caracteres numericos.");
                            scanner.nextLine();
                            continue;
                        }

                        // Empleado
                        if (op == 1) {
                            System.out.println("Ingrese el ID del empleado: ");
                            Empleado empleado = Empleado.checarExistenciaEmpleado();

                            scanner.nextLine();

                            System.out.println("Ingrese los dias laborados por: " + empleado.getNombre() +
                                    " " + empleado.getApellido());

                            empleado.registrarDiasLaboradosEmpleado();

                        // Todos los empleados
                        } else if (op == 2) {
                            System.out.println("Ingrese la cantidad de horas laboradas: ");

                            // Registrar dias trabajados por todos los empleados
                            Empleado.registrarDiasLaborados();
                        }

                        if (op == 3) {
                            break;
                        }
                    } while (true);
                }
                case 5 -> {
                    // Registrar horas extra
                    int op;

                    /*
                     * Desplegar menu para que el usuario indique si registrar las horas extra de un empleado en
                     * particular o de todos los empleados
                     */
                    do {
                        Menu.menuDeOpciones();
                        try {
                            op = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opcion invalida. Solo se permiten caracteres numericos.");
                            scanner.nextLine();
                            continue;
                        }

                        // Empleado
                        if (op == 1) {
                            System.out.println("Ingrese el ID del empleado: ");
                            Empleado empleado = Empleado.checarExistenciaEmpleado();

                            System.out.println("Ingrese las horas extra de: " + empleado.getNombre() +
                                    " " + empleado.getApellido());

                            // Registrar horas extra del empleado
                            empleado.registrarHorasExtraEmpleado();

                        // Todos los empleados
                        } else if (op == 2) {
                            System.out.println("Ingrese la cantidad de horas extra: ");

                            // Registrar horas extra de todos los empleados
                            Empleado.registrarHorasExtra();
                        }

                        if (op == 3) {
                            break;
                        }
                    } while (true);
                }
                case 6 -> {
                    int op;
                    do {
                        Menu.menuRegistro();
                        try {
                            op = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opcion invalida. Solo se permiten caracteres numericos.");
                            scanner.nextLine();
                            continue;
                        }

                        if(op == 1) {
                            Puesto.verRegistroDePuestos();
                        } else if (op == 2) {
                            // Registrar un nuevo puesto
                            System.out.println("Por favor, ingrese la informacion que se solicita.");

                            // Verificar que el ID del nuevo puesto no corresponda al de uno ya registrado
                            int idPuesto = Puesto.checarPuestoPorID();
                            scanner.nextLine();

                            // Verificar que el nombre del nuevo puesto no corresponda al de uno ya registrado
                            System.out.println("Nombre: ");
                            String nombrePuesto;
                            Puesto nuevoPuesto;
                            do {
                                nombrePuesto = scanner.nextLine();
                                nuevoPuesto = Puesto.buscarPuestoPorNombre(nombrePuesto);

                                if (nuevoPuesto != null) {
                                    System.out.println("El puesto ingresado ya existe. Por favor, ingrese un nombre valido.");
                                } else {
                                    break;
                                }
                            } while (true);

                            // Registro de salario por dia y por hora extra de acuerdo al puesto.
                            System.out.println("Salario por dia: ");
                            double salarioPorDia;
                            try {
                                salarioPorDia = scanner.nextDouble();
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor, ingrese una cantidad valida.");
                                scanner.nextLine();
                                continue;
                            }

                            System.out.println("Salario por hora extra: ");
                            double salarioPorHoraExtra;
                            try {
                                salarioPorHoraExtra = scanner.nextDouble();
                            } catch (InputMismatchException e) {
                                System.out.println("Por favor, ingrese una cantidad valida.");
                                scanner.nextLine();
                                continue;
                            }

                            // Agregar el nuevo puesto al registro
                            Puesto.getRegistroDePuestos().add(new Puesto(idPuesto, nombrePuesto, salarioPorDia,
                                    salarioPorHoraExtra));
                            System.out.println("¡Registro exitoso!");
                        } else {
                            break;
                        }
                    } while (true);
                }
                case 7 -> {
                    // Calcular nomina
                    int op;
                    /*
                     * Desplegar menu para que el usuario indique si calcular la nomina de un empleado en particular
                     * o de toda la empresa
                     */
                    do {
                        Menu.menuDeOpciones();
                        try {
                            op = scanner.nextInt();
                        } catch (InputMismatchException e) {
                            System.out.println("Opcion invalida. solo se permiten caracteres numericos.");
                            scanner.nextLine();
                            continue;
                        }

                        // Empleado
                        if (op == 1) {
                            System.out.println("Ingrese el ID del empleado: ");

                            // Mostrar en pantalla la nomina del empleado
                            empresa.calcularNominaEmpleado();

                        // Empresa
                        } else if (op == 2) {
                            // Calcular nomina de la empresa
                            empresa.calcularNominaEmpresa();
                            System.out.println("¡Calculo exitoso!");

                            // Preguntar al usuario si desea almacenar la informacion en el historial
                            empresa.guardarNomina();
                            System.out.println("Nomina almacenada. Para visualizarla en el historial, por favor, " +
                                    "regrese al menu principal y seleccione la opcion correspondiente.");
                        }

                        if (op == 3)  {
                            break;
                        }
                    } while (true);

                }
                case 8 -> {
                    // Visualizar historial de nomina en pantalla
                    System.out.println("----- Historial de nomina -----");
                    empresa.verHistorialDeNomina();
                    System.out.println("-------------------------------");
                    System.out.println();
                }
                default -> System.out.println("¡Hasta luego!");
            }

            if (opcion == 9) {
                break;
            }
        } while (true);
    }
}
