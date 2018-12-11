package co.com.ceiba.ceibaParqueadero.integracion;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import co.com.ceiba.ceibaParqueadero.persistencia.repository.VehiculoRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
public class vigilanteControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
	private VehiculoRepository vehiculoRepository;
	
	@Before
	public void setUp() {
		vehiculoRepository.eliminarTodo();
	}

	@Test
	public void consultaVehiculosTest() throws Exception {
		mockMvc.perform(get("/vehiculos"))
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
			.andExpect(content().json("[]"));
	}
	
	/*
	@Test
	public void registrarVehiculosTest() throws Exception {
		Vehiculo vehiculo = new VehiculoTestDataBuilder().build();
		mockMvc.perform(post("/vehiculos")
						//.accept(MediaType.APPLICATION_JSON)
						.param("placa", vehiculo.getPlaca())
						.param("tipo", vehiculo.getTipo())
						.param("cilindraje", String.valueOf(vehiculo.getCilindraje()))
						.param("modelo", vehiculo.getModelo())
						.param("color", vehiculo.getColor())
						.param("clase", vehiculo.getClase())
						.param("marca", vehiculo.getMarca())
				).andDo(print())
			.andExpect(status().isOk())
			.andExpect(content().contentType(MediaType.APPLICATION_JSON))
			.andExpect(content().json("{'status':'Ok','message':'"+Constants.VEHICULO_CREADO+"'}"));
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	} 
	*/

}
