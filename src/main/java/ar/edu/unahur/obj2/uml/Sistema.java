package ar.edu.unahur.obj2.uml;

import java.util.ArrayList;

public class Sistema {

    // datos fijos
    private final ArrayList<Alquiler> alquileresRegistrados;
    private final ArrayList<Vehiculo> vehiculos;

    // . Ocultamos la única "copia" dentro de la misma clase.
    // Es estática para que pertenezca a la clase y no a una instancia.
    private static Sistema instanciaUnica;

    // . La "puerta de entrada" accesible para todo el mundo.
    // Este método revisa si el objeto ya existe. Si no, lo crea. Si ya existe, lo devuelve.
    public static Sistema getInstance() {
        if (instanciaUnica == null) {
            instanciaUnica = new Sistema();
        }
        return instanciaUnica;
    }

    private Sistema() {
        this.alquileresRegistrados = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
    }

    public void registrarNuevoAlquiler(Integer fechaInicio, Integer fechaFin, Usuario usuario, Vehiculo vehiculo) {
        Alquiler nuevoAlquiler = new Alquiler(fechaInicio, fechaFin, usuario, vehiculo);
        alquileresRegistrados.add(nuevoAlquiler);
    }

    public ArrayList<Alquiler> getAlquileresRegistrados() {
        return alquileresRegistrados;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        vehiculos.add(vehiculo);
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public Integer cantidadDeVehiculosDisponibles() {
        Integer cantidad = 0;
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.sePuedeAlquilar()) {
                cantidad++;
            }
        }
        return cantidad;
    }

    public void limpiarAlquileresRegistrados() {
        alquileresRegistrados.clear();
    }

    public void limpiarVehiculosRegistrados() {
        vehiculos.clear();
    }

}
