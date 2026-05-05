package ar.edu.unahur.obj2.uml;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/*

 Criterios de aceptación:

 El sistema debe permitir:

  - Alquilar un vehículo disponible .ok
  - Actualizar el estado del vehículo según corresponda .ok
  - Registrar la operación de alquiler .ok

  - Consultar:
  - los vehículos disponibles .ok
  - los alquileres realizados .ok

*/

public class VehiculosTest {

    @Test
    public void cuandoSeCreaUnVehiculoEsteSePuedeAlquilar() {
        Vehiculo vehiculo = new Vehiculo();
        assertTrue(vehiculo.sePuedeAlquilar());
    }

    @Test
    public void cuandoSeAlquilaUnVehiculoEsteYaNoSePuedeAlquilar() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.alquilar(vehiculo);
        assertFalse(vehiculo.sePuedeAlquilar());
    }

    @Test
    public void cuandoSeCreaUnaBicicletaEstaSePuedeAlquilar() {
        Bicicleta bicicletaNueva = new Bicicleta(26);
        assertTrue(bicicletaNueva.sePuedeAlquilar());
    }

    @Test
    public void cuandoSeCreaUnaBicicletaEstaTieneUnRodado() {
        Bicicleta bicicletaNueva = new Bicicleta(26);
        assertEquals(26, bicicletaNueva.getRodado());
    }

    @Test
    public void cuandoSeCreaUnMonopatinEsteSePuedeAlquilar() {
        Monopatin monopatinNuevo = new Monopatin("Xiaomi");
        assertTrue(monopatinNuevo.sePuedeAlquilar());
    }

    @Test
    public void cuandoSeCreaUnMonopatinEsteTieneUnaMarca() {
        Monopatin monopatinNuevo = new Monopatin("Xiaomi");
        assertEquals("Xiaomi", monopatinNuevo.getMarca());
    }

    @Test
    public void cuandoUnUsuarioAlquilaUnVehiculoEsteYaNoSePuedeAlquilar() {
        Usuario usuarioNuevo = new Usuario("Juan");
        Vehiculo vehiculoNuevo = new Vehiculo();
        usuarioNuevo.alquilarVehiculo(vehiculoNuevo);
        assertFalse(vehiculoNuevo.sePuedeAlquilar());
    }

    @Test
    public void cuandoUnUsuarioAlquilaUnVehiculoEsteSeAgregaALaListaDeVehiculosAlquilados() {
        Usuario usuarioNuevo = new Usuario("Marcelo");
        Vehiculo vehiculoNuevo = new Vehiculo();
        usuarioNuevo.alquilarVehiculo(vehiculoNuevo);
    }

    // un solo sistema unico 
    Sistema sistemaNuevo = Sistema.getInstance();

    @Test
    public void cuandoSeRegistraUnAlquilerEsteSeAgregaALaListaDeAlquileresRegistradosDelSistema() {
        Usuario lucia = new Usuario("Lucia");
        Vehiculo unVehiculo = new Bicicleta(26);
        sistemaNuevo.registrarNuevoAlquiler(1, 5, lucia, unVehiculo);
        assertEquals(1, sistemaNuevo.getAlquileresRegistrados().size());
    }

    @Test
    public void cuandoSeRegistraUnAlquilerEsteContieneLaInformacionCorrespondiente() {
        // hacemos un reset a la lista de alquileres registrados para evitar errores por otros test realizados anteriormente
        sistemaNuevo.limpiarAlquileresRegistrados(); 
        
        Usuario lucia = new Usuario("Lucia");
        Vehiculo unVehiculo = new Monopatin("Xiaomi");
        sistemaNuevo.registrarNuevoAlquiler(2, 3, lucia, unVehiculo);
        Alquiler primerAlquilerRegistrado = sistemaNuevo.getAlquileresRegistrados().get(0);

        // Verificamos que la información guardada del alquiler recien registrado sea
        // correcta
        assertEquals(2, primerAlquilerRegistrado.getFechaInicio());
        assertEquals(3, primerAlquilerRegistrado.getFechaFin());
        assertEquals(lucia, primerAlquilerRegistrado.getUsuario());
        assertEquals(unVehiculo, primerAlquilerRegistrado.getVehiculo());
    }

    @Test
    public void cuandoSeCreaUnVehiculoNuevoYSeLoAgregaAlSistemaEsteSeAgregaALaListaDeVehiculos() {
        // hacemos un reset a la lista de alquileres registrados para evitar errores por otros test realizados anteriormente
        sistemaNuevo.limpiarAlquileresRegistrados(); 
        
        Vehiculo unVehiculo = new Bicicleta(26);
        sistemaNuevo.registrarVehiculo(unVehiculo);
        assertEquals(1, sistemaNuevo.getVehiculos().size());
    }

    @Test
    public void cuandoSeCreaMasDeUnVehiculoNuevoYSeLosAgregaAlSistemaEstosSeAgreganALaListaDeVehiculosYSePuedenConsultarSiEstanDisponibles() {
        // hacemos un reset a la lista de alquileres y de vehiculos registrados para evitar errores por otros test realizados anteriormente
        sistemaNuevo.limpiarAlquileresRegistrados();
        sistemaNuevo.limpiarVehiculosRegistrados(); 
        
        Vehiculo vehiculo1 = new Bicicleta(26);
        Vehiculo vehiculo2 = new Monopatin("Xiaomi");

        sistemaNuevo.registrarVehiculo(vehiculo1);
        sistemaNuevo.registrarVehiculo(vehiculo2);

        // Consultamos la cantidad de vehículos disponibles en el sistema,
        // que debería ser 2 ya que ambos vehículos se acaban de agregar y ninguno fue
        // alquilado
        assertEquals(2, sistemaNuevo.cantidadDeVehiculosDisponibles());
    }

    @Test
    public void cuandoSeCreaMasDeUnVehiculoNuevoYSeLosAgregaAlSistemaEstosSeAgreganALaListaDeVehiculosPeroSiUnoEsAlquiladoNoSeCuentaComoDisponible() {
        // hacemos un reset a la lista de alquileres y de vehiculos registrados para evitar errores por otros test realizados anteriormente
        sistemaNuevo.limpiarAlquileresRegistrados();
        sistemaNuevo.limpiarVehiculosRegistrados();

        Vehiculo vehiculo1 = new Bicicleta(26);
        Vehiculo vehiculo2 = new Monopatin("Xiaomi");

        sistemaNuevo.registrarVehiculo(vehiculo1);
        sistemaNuevo.registrarVehiculo(vehiculo2);

        // Alquilamos uno de los vehículos
        Usuario usuario = new Usuario("Juan");
        usuario.alquilarVehiculo(vehiculo1);

        // Consultamos la cantidad de vehículos disponibles en el sistema,
        // que debería ser 1 ya que uno de ellos fue alquilado
        assertEquals(1, sistemaNuevo.cantidadDeVehiculosDisponibles());
    }
}