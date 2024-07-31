package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.Curso_Paralelo;
import ec.com.webmarket.restful.dto.v1.Curso_ParaleloDTO;
import ec.com.webmarket.restful.persistence.Curso_ParaleloRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class Curso_ParaleloService extends GenericCrudServiceImpl<Curso_Paralelo, Curso_ParaleloDTO> {

	@Autowired
	private Curso_ParaleloRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Curso_Paralelo> find(Curso_ParaleloDTO dto) {
		// Buscar por nombre o cédula en lugar de ID
		if (dto.getCurso() != null) {
			List<Curso_Paralelo> curso_paralelo = repository.findByCurso(dto.getCurso());
			if (!curso_paralelo.isEmpty()) {
				return Optional.of(curso_paralelo.get(0)); // Retornar el primer elemento encontrado
			}
		}
		if (dto.getParalelo() != null) {
			List<Curso_Paralelo> curso_paralelo = repository.findByParalelo(dto.getParalelo());
			if (!curso_paralelo.isEmpty()) {
				return Optional.of(curso_paralelo.get(0));
			}
		}
		return Optional.empty();
	}

	@Override
	public Curso_Paralelo mapToDomain(Curso_ParaleloDTO dto) {
		return modelMapper.map(dto, Curso_Paralelo.class);
	}

	@Override
	public Curso_ParaleloDTO mapToDto(Curso_Paralelo domain) {
		return modelMapper.map(domain, Curso_ParaleloDTO.class);
	}

}
