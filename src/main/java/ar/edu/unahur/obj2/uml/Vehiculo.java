package ar.edu.unahur.obj2.uml;

public class Vehiculo {

    private Boolean sePuedeAlquilar = true;

    public Boolean sePuedeAlquilar() {
        return sePuedeAlquilar;
    }

    public void alquilar(Vehiculo vehiculo) {
        if (sePuedeAlquilar) {
            sePuedeAlquilar = false;
        }
    }   
  
}
