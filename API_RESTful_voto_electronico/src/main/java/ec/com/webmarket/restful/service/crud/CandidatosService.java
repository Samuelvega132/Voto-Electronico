package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.Candidatos;
import ec.com.webmarket.restful.dto.v1.CandidatosDTO;
import ec.com.webmarket.restful.persistence.CandidatosRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class CandidatosService extends GenericCrudServiceImpl<Candidatos, CandidatosDTO> {

	@Autowired
	private CandidatosRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<Candidatos> find(CandidatosDTO dto) {
		// Buscar por nombre o cédula en lugar de ID
		if (dto.getNombre() != null) {
			List<Candidatos> candidatos = repository.findByNombre(dto.getNombre());
			if (!candidatos.isEmpty()) {
				return Optional.of(candidatos.get(0)); // Retornar el primer elemento encontrado
			}
		}
		if (dto.getPartido() != null) {
			List<Candidatos> candidatos = repository.findByPartido(dto.getPartido());
			if (!candidatos.isEmpty()) {
				return Optional.of(candidatos.get(0));
			}
		}
		return Optional.empty();
	}

	@Override
	public Candidatos mapToDomain(CandidatosDTO dto) {
		return modelMapper.map(dto, Candidatos.class);
	}

	@Override
	public CandidatosDTO mapToDto(Candidatos domain) {
		return modelMapper.map(domain, CandidatosDTO.class);
	}

}
