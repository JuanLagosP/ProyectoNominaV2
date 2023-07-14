package nomina;

import java.util.InputMismatchException;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Puesto {
    private int idPuesto;
    private String nombrePuesto;
    private double salarioPorDia;
    private double salarioPorHoraExtra;
    private static List<Puesto> registroDePuestos;

    // Getters y Setters
    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public String getNombrePuesto() {
        return nombrePuesto;
    }

    public void setNombrePuesto(String nombrePuesto) {
        this.nombrePuesto = nombrePuesto;
    }

    public double getSalarioPorDia() {
        return salarioPorDia;
    }

    public void setSalarioPorDia(double salarioPorDia) {
        this.salarioPorDia = salarioPorDia;
    }

    public double getSalarioPorHoraExtra() {
        return salarioPorHoraExtra;
    }

    public void setSalarioPorHoraExtra(double salarioPorHoraExtra) {
        this.salarioPorHoraExtra = salarioPorHoraExtra;
    }

    public static List<Puesto> getRegistroDePuestos() {
        return registroDePuestos;
    }

    public static void setRegistroDePuestos(List<Puesto> registroDePuestos) {
        Puesto.registroDePuestos = registroDePuestos;
    }

    // Constructor predeterminado
    public Puesto() {
        this.idPuesto = 0;
        this.nombrePuesto = null;
        this.salarioPorDia = 0.0;
        this.salarioPorHoraExtra = 0.0;
    }

    // Constructor
    public Puesto(int idPuesto, String nombrePuesto, double salarioPorDia, double salarioPorHoraExtra) {
        this.idPuesto = idPuesto;
        this.nombrePuesto = nombrePuesto;
        this.salarioPorDia = salarioPorDia;
        this.salarioPorHoraExtra = salarioPorHoraExtra;
    }

    // Metodos
    public static void inicializarRegistroDePuestos() {
        registroDePuestos = new ArrayList<>();
    }

    public static void registrarPuesto(Puesto puesto) {
        registroDePuestos.add(puesto);
    }

    public static void verRegistroDePuestos() {
        for (Puesto p : registroDePuestos) {
            System.out.printf("%s%n %s%n %s%n %s%n",
                    "ID: " + p.getIdPuesto(),
                    "Puesto: " + p.getNombrePuesto(),
                    "Salario por dia: $" + p.getSalarioPorDia(),
                    "Pago por hora extra: $" + p.getSalarioPorHoraExtra() + "\n");
        }
    }

    public void eliminarPuesto(Puesto puesto) {
        registroDePuestos.remove(puesto);
    }

    public static Puesto buscarPuestoPorNombre(String nombrePuesto) {
        for (Puesto puesto : registroDePuestos) {
            if (puesto.getNombrePuesto().equalsIgnoreCase(nombrePuesto)) {
                return puesto;
            }
        }
        return null;
    }

    public static Puesto buscarPuestoPorID(int idPuesto) {
        for (Puesto puesto : registroDePuestos) {
            if (puesto.getIdPuesto() == idPuesto) {
                return puesto;
            }
        }
        return null;
    }

    public static Puesto checarExistenciaPuesto() {
        Puesto puesto;
        do {
            puesto = buscarPuestoPorNombre(new Scanner(System.in).nextLine());
            if (puesto == null) {
                System.out.println("El puesto ingresado no existe. Por favor, ingrese un puesto valido: ");
            }
        } while (puesto == null);

        return puesto;
    }

    public static int checarPuestoPorID() {
        int idPuesto;
        Puesto puesto;

        do {
            try {
                System.out.println("ID: ");
                idPuesto = new Scanner(System.in).nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Solo se permiten caracteres numericos.");
                new Scanner(System.in).nextLine();
                continue;
            }
            puesto = buscarPuestoPorID(idPuesto);

            if (puesto != null) {
                System.out.println("El ID ingresado ya pertenece a un puesto. Por favor, ingrese un nuevo ID: ");
            } else {
                return idPuesto;
            }
        } while (true);
    }


}