package mx.edu.itlapiedad.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mx.edu.itlapiedad.models.Productos;
import mx.edu.itlapiedad.services.ProductoService;



@RestController
@RequestMapping("/api/devops/oxxo")
public class ProductosWS {

	@Autowired
	ProductoService servicio;	
	
@GetMapping("/productos")
public ResponseEntity<?> consultarProductos() {
	List<Productos>resultado;
	try {
		resultado= servicio.consultarProductos();
	}catch(DataAccessException e) {
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	return new ResponseEntity<List<Productos>>(resultado, HttpStatus.OK);
	
}

@PostMapping()
public ResponseEntity<?>insertarProductos(@RequestBody Productos productos){
	Productos resultado=null;
	try {
		resultado=servicio.insertarProductos(productos);
	}catch(DataAccessException e) {
		return new ResponseEntity<>(HttpStatus.CONFLICT);
	}
	
	return new ResponseEntity<Productos>(resultado,HttpStatus.CREATED);
	
}
}
