package br.com.albacares.bluefood.domain.usuario;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import br.com.albacares.bluefood.util.StringUtils;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
@Getter // implementa os metodos getters implicitamente
@Setter  // implementa os metodos setters implicitamente
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // implementa os metodos euqals e hashCode, nesse caso apenas os atributos informados
@MappedSuperclass   // mapeia a superclasse para a entidade
public class Usuario implements Serializable{

	public Usuario() {
	
	}

	@EqualsAndHashCode.Include  // inclui como atributo que terá os métodos hashCode e equals
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	
	@NotBlank(message ="O nome não pode ser vazio")
	@Size(max=80, message = "O nome é muito grande")
	@Column(nullable = false)
	private String nome;
	
	@NotBlank(message ="O e-mail não pode ser vazio")
	@Size(max=60, message = "O e-mail é muito grande")
	@Email(message = "O e-mail é inválido")
	@Column(nullable = false)
	private String email;
	
	@NotBlank(message ="A senha não pode ser vazia")
	@Size(max=80, message = "A senha é muito grande")
	@Column(nullable = false)
	private String senha;
	
	@NotBlank(message ="O telefone não pode ser vazio")
	@Pattern(regexp = "[0-9]{11}" , message ="O telefone possui formato inválido" )
	@Column(length = 11, nullable = false)
	private String telefone;
	
	public void encryptPassword() {
		this.senha = StringUtils.encrypt(this.senha);
	}

}
