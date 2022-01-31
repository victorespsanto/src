package br.com.albacares.bluefood.domain.cliente;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import br.com.albacares.bluefood.domain.usuario.Usuario;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter  // implementa os metodos getters implicitamente
@Setter	 // implementa os metodos setters implicitamente
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true) // implementa os metodos euqals e hashCode, nesse caso apenas os atributos informados
@Entity
public class Cliente extends Usuario {	
	
	public Cliente() {
		
	}

	@NotBlank(message = "O CPF não pode ser vazio")
	@Pattern(regexp = "[0-9]{11}", message = "O CPF possui formato inválido")
	@Column(length = 11, nullable = false)
	private String cpf;
	
	@NotBlank(message = "O CEP não pode ser vazio")
	@Pattern(regexp = "[0-9]{8}", message = "O CEP possui formato inválido")
	@Column(length = 8, nullable = false)
	private String cep;

}
