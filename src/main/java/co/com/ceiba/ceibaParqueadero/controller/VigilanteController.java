package co.com.ceiba.ceibaParqueadero.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import co.com.ceiba.ceibaParqueadero.dominio.Vigilante;
import co.com.ceiba.ceibaParqueadero.dominio.modelo.Vehiculo;
import co.com.ceiba.ceibaParqueadero.exception.ParqueaderoException;
import co.com.ceiba.ceibaParqueadero.util.Constants;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/vehiculos")
public class VigilanteController {
	
	@Autowired
	Vigilante vigilante;

	@GetMapping("detalles")
	@ResponseBody
	public Object consultaVehiculosDetalles(){
			return vigilante.obtenerTodosLosVehiculos();	
	}
	
	@GetMapping()
	@ResponseBody
	public List<VehiculoRestDto> consultaVehiculos(){
			List<VehiculoRestDto> vehiculosRest = new ArrayList<>();
			List<Vehiculo> vehiculos = vigilante.obtenerTodosLosVehiculos();
			for(Vehiculo vehiculo: vehiculos){
				vehiculosRest.add(new VehiculoRestDto(vehiculo.getPlaca(),vehiculo.getTipo(),vehiculo.getFechaIngreso()));
			}
			return vehiculosRest;
	}
	
	
	@PostMapping()
	public String registrarVehiculo(@Valid @RequestBody Vehiculo vehiculo) throws ParqueaderoException{
			vigilante.registrarVehiculo(vehiculo);
			return JSONObject.quote(Constants.VEHICULO_CREADO); 
	}
	

	@DeleteMapping("/{placa}")
	@ResponseBody
	public String salidaVehiculo(@PathVariable(value="placa") String placa) throws ParqueaderoException, JSONException{
			return JSONObject.numberToString(vigilante.salidaVehiculo(placa));
	}
	
	

}
