package co.com.ceiba.CeibaParqueadero.Controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Persistencia.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Persistencia.Repository.VehiculoRepository;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	VehiculoRepository vehiculoRepository;

	/**
	 * Obtener todos los vehiculos
	 * @return
	 */
	@GetMapping()
	public List<Vehiculo> obtenerTodos(){
		return vehiculoRepository.findAll();
	}
	
	/**
	 * Registrar un nuevo vehiculo
	 * @param vehiculo
	 * @return
	 */
	@PostMapping()
	public Vehiculo crearVehiculo(@Valid @RequestBody Vehiculo vehiculo){
		return vehiculoRepository.save(vehiculo);
	}
	
	/**
	 * Obtener vehiculo dada su placa
	 * @param placa
	 * @return
	 * @throws ParqueaderoException
	 */
	@GetMapping("/{placa}")
	public Vehiculo obtenerVehiculoPorPlaca(@PathVariable(value="placa") String placa) throws ParqueaderoException {
		return vehiculoRepository.findById(placa)
				.orElseThrow(()-> new ParqueaderoException("resource no found"));
	}
	
	/**
	 * Eliminar vehiculo dada su placa
	 * @param placa
	 * @return
	 * @throws ParqueaderoException
	 */
	@DeleteMapping("/{placa}")
	public ResponseEntity<String> eliminarVehiculo(@PathVariable(value="placa") String placa) throws ParqueaderoException{
		Vehiculo vehiculo = vehiculoRepository.findById(placa)
				.orElseThrow(()-> new ParqueaderoException("resource no found"));
		
		vehiculoRepository.delete(vehiculo);
		return ResponseEntity.ok("hola");
	}
	
	/**
	 * Obtener la cantidad de vehiculos
	 * @return
	 */
	@GetMapping("/cantidad")
	public Long obetenerCantidadVehiculos(){
		return vehiculoRepository.count();
	}
	

}
