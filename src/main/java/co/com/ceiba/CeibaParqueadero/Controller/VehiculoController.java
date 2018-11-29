package co.com.ceiba.CeibaParqueadero.Controller;

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

import co.com.ceiba.CeibaParqueadero.Dominio.VehiculoRepository;
import co.com.ceiba.CeibaParqueadero.Dominio.Modelo.Vehiculo;
import co.com.ceiba.CeibaParqueadero.Exception.ParqueaderoException;
import co.com.ceiba.CeibaParqueadero.Util.Constants;

@RestController
@RequestMapping("/vehiculos")
public class VehiculoController {
	
	@Autowired
	VehiculoRepository vehiculoRepository;

	@GetMapping()
	public Object obtenerTodos(){
		try {
			return vehiculoRepository.obtenerTodosLosVehiculos();
		} catch (Exception e) {
			return ResponseEntity.ok(new Respuesta("BAD REQUEST",e.getMessage()));
		}
		
	}
	
	
	@PostMapping()
	public ResponseEntity<Respuesta> crearVehiculo(@Valid @RequestBody Vehiculo vehiculo){
		try {
			vehiculoRepository.crearVehiculo(vehiculo);
			return ResponseEntity.ok(new Respuesta(Constants.STATUS_OK,Constants.VEHICULO_CREADO));
		}catch(Exception e) {
			return ResponseEntity.ok(new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage()));
		}
	}
	
	
	@GetMapping("/{placa}")
	public Object obtenerVehiculoPorPlaca(@PathVariable(value="placa") String placa) throws ParqueaderoException {
		try {
			return vehiculoRepository.obtenerVehiculoPorPlaca(placa);
		}catch(Exception e) {
			return ResponseEntity.ok(new Respuesta("BAD REQUEST",e.getMessage()));
		}
	}
	
	
	@DeleteMapping("/{placa}")
	public ResponseEntity<Respuesta> eliminarVehiculo(@PathVariable(value="placa") String placa) throws ParqueaderoException{
		try {
			vehiculoRepository.eliminarVehiculo(placa);
			return ResponseEntity.ok(new Respuesta(Constants.STATUS_OK,Constants.VEHICULO_ELIMINADO));
		}catch(Exception e) {
			return ResponseEntity.ok(new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage()));
		}
	}
	
	
	@GetMapping("/cantidad")
	public ResponseEntity<Respuesta> obetenerCantidadVehiculos(){
		try {
			return ResponseEntity.ok(new Respuesta(Constants.STATUS_OK,vehiculoRepository.obtenerCantidadVehiculos().toString()));
		}catch(Exception e) {
			return ResponseEntity.ok(new Respuesta(Constants.STATUS_BAD_REQUEST,e.getMessage()));
		}
	}
	

}
