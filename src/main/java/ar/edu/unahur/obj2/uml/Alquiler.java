package ar.edu.unahur.obj2.uml;

import java.util.ArrayList;

public class Alquiler {

    private Integer fechaInicio;
    private Integer fechaFin;
    private Usuario usuario;
    private Vehiculo vehiculo;

    public Alquiler(Integer fechaInicio, Integer fechaFin, Usuario usuario, Vehiculo vehiculo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
    }

    public Integer getFechaInicio() {
        return fechaInicio;
    }

    public Integer getFechaFin() {
        return fechaFin;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

}
