package ec.com.webmarket.restful.service.crud;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ec.com.webmarket.restful.domain.MesasElectorales;
import ec.com.webmarket.restful.dto.v1.MesasElectoralesDTO;
import ec.com.webmarket.restful.persistence.MesasElectoralesRepository;
import ec.com.webmarket.restful.service.GenericCrudServiceImpl;

@Service
public class MesasElectoralesService extends GenericCrudServiceImpl<MesasElectorales, MesasElectoralesDTO> {

	@Autowired
	private MesasElectoralesRepository repository;

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public Optional<MesasElectorales> find(MesasElectoralesDTO dto) {
		// Buscar por nombre o cédula en lugar de ID
		if (dto.getNombre() != null) {
			List<MesasElectorales> mesaselectorales = repository.findByNombre(dto.getNombre());
			if (!mesaselectorales.isEmpty()) {
				return Optional.of(mesaselectorales.get(0)); // Retornar el primer elemento encontrado
			}
		}
		return Optional.empty();
	}

	@Override
	public MesasElectorales mapToDomain(MesasElectoralesDTO dto) {
		return modelMapper.map(dto, MesasElectorales.class);
	}

	@Override
	public MesasElectoralesDTO mapToDto(MesasElectorales domain) {
		return modelMapper.map(domain, MesasElectoralesDTO.class);
	}

}