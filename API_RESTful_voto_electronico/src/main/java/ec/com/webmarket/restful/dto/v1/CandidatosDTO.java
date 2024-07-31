package ec.com.webmarket.restful.dto.v1;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CandidatosDTO {

	private Long id;
	
	@NotBlank(message = "El nombre no debe estar en blanco")
    private String nombre;	
	
	@NotBlank(message = "El partido no debe estar en blanco")
    private String partido;
}
