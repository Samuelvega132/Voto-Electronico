package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.Estudiantes;
import ec.com.webmarket.restful.dto.v1.EstudiantesDTO;
import ec.com.webmarket.restful.persistence.EstudiantesRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;


@Service
public class EstudiantesService extends GenericCrudServiceImpl<Estudiantes, EstudiantesDTO> {

	@Autowired
	private EstudiantesRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Estudiantes> find(EstudiantesDTO dto) {
		// Buscar por nombre o cédula en lugar de ID
		if (dto.getNombre() != null) {
			List<Estudiantes> estudiantes = repository.findByNombre(dto.getNombre());
			if (!estudiantes.isEmpty()) {
				return Optional.of(estudiantes.get(0)); // Retornar el primer elemento encontrado
			}
		}
		if (dto.getCedula() != null) {
			List<Estudiantes> estudiantes = repository.findByCedula(dto.getCedula());
			if (!estudiantes.isEmpty()) {
				return Optional.of(estudiantes.get(0));
			}
		}
		return Optional.empty();
	}

	@Override
	public Estudiantes mapToDomain(EstudiantesDTO dto) {
		return modelMapper.map(dto, Estudiantes.class);
	}

	@Override
	public EstudiantesDTO mapToDto(Estudiantes domain) {
		return modelMapper.map(domain, EstudiantesDTO.class);
	}

}
