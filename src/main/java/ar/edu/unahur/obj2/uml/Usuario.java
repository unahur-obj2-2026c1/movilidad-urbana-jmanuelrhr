package ar.edu.unahur.obj2.uml;

import java.util.*;

public class Usuario {

    private String nombre;
    protected ArrayList<Vehiculo> vehiculosAlquilados;

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.vehiculosAlquilados = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void alquilarVehiculo(Vehiculo vehiculo) {
        if (vehiculo.sePuedeAlquilar()) {
            vehiculo.alquilar(vehiculo);
            vehiculosAlquilados.add(vehiculo);
        }
    }

}
