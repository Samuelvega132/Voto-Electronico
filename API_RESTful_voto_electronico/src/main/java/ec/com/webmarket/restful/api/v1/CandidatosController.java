package ec.com.webmarket.restful.api.v1;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ec.com.webmarket.restful.common.ApiConstants;
import ec.com.webmarket.restful.common.ApiException;
import ec.com.webmarket.restful.domain.Candidatos;
import ec.com.webmarket.restful.dto.v1.CandidatosDTO;
import ec.com.webmarket.restful.security.ApiResponseDTO;
import ec.com.webmarket.restful.service.crud.CandidatosService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { ApiConstants.URI_API_V1_CANDIDATOS })
public class CandidatosController {

	@Autowired
	private CandidatosService entityService;

	@GetMapping
	public ResponseEntity<?> getAll() {
		return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.findAll(new CandidatosDTO())), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody CandidatosDTO dto) {
		// No permitir IDs al crear
		if (dto.getId() != null) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El ID debe ser nulo al crear un nuevo estudiante"));
		}

		try {
			return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.create(dto)), HttpStatus.CREATED);
		} catch (ApiException e) {
			// Manejo de excepción personalizada
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, e.getMessage()));
		} catch (Exception e) {
			// Manejo de otras excepciones generales
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO<>(false, "Error interno del servidor"));
		}
	}

	@PutMapping
	public ResponseEntity<?> update(@RequestBody CandidatosDTO dto) {
		// El ID es necesario para actualizar
		if (dto.getId() == null) {
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, "El ID no debe ser nulo al actualizar un estudiante"));
		}

		try {
			return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.update(dto)), HttpStatus.OK);
		} catch (ApiException e) {
			// Manejo de excepción personalizada
			return ResponseEntity.badRequest().body(new ApiResponseDTO<>(false, e.getMessage()));
		} catch (Exception e) {
			// Manejo de otras excepciones generales
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponseDTO<>(false, "Error interno del servidor"));
		}
	}

	@GetMapping("/{id}/archivo/id")
	public ResponseEntity<?> getById(@Valid @PathVariable Long id) {
		CandidatosDTO dto = new CandidatosDTO();
		dto.setId(id);
		Optional<Candidatos> candidatos = entityService.find(dto);
		if (candidatos.isPresent()) {
			return new ResponseEntity<>(new ApiResponseDTO<>(true, entityService.mapToDto(candidatos.get())), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(new ApiResponseDTO<>(false, "Estudiante no encontrado"), HttpStatus.NOT_FOUND);
		}
	}
}