package nomina;

import java.util.Comparator;

public interface Nomina {
    public void calcularNominaEmpleado();
    public void calcularNominaEmpresa();
    public void inicializarHistorialDeNomina();
    public void guardarNomina();
    public void verHistorialDeNomina();
    public void verHistorialOrdenAlfabetico();
    public void verHistorialMayorNomina();
    public void verHistorialMenorNomina();
}
