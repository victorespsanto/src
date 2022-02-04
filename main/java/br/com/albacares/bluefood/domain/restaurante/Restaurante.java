package br.com.albacares.bluefood.domain.restaurante;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

import br.com.albacares.bluefood.domain.usuario.Usuario;
import br.com.albacares.bluefood.infrastructure.web.validator.UploadConstraint;
import br.com.albacares.bluefood.util.FileType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@SuppressWarnings("serial")
@Getter  // implementa os metodos getters implicitamente
@Setter	 // implementa os metodos setters implicitamente
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true) // implementa os metodos euqals e hashCode, nesse caso apenas os atributos informados
@Entity
@Table(name = "restaurante")
public class Restaurante extends Usuario {
	
	public Restaurante() {	
	}

	@NotBlank(message = "O CNPJ não pode ser vazio")
	@Pattern(regexp = "[0-9]{14}", message = "O CNPJ possui formato inválido")
	@Column(length = 14, nullable = false)	
	private String cnpj;

	@Size(max = 80)
	private String logotipo;
	
	@UploadConstraint(acceptedTypes = FileType.PNG, message = "O arquivo não é um arquivo de imagem válido")
	private transient MultipartFile logotipoFile;

	@NotNull(message = "A taxa de entrega não pode ser vazia")
	@Min(0)
	@Max(99)
	private BigDecimal taxaEntrega;
	
	@NotNull(message = "O tempo de entrega não pode ser vazio")
	@Min(0)
	@Max(120)
	private Integer tempoEntregaBase;
	
	@ManyToMany
	@JoinTable(
			name = "restaurante_has_categoria",
			joinColumns = @JoinColumn(name = "restaurante_id"),
			inverseJoinColumns = @JoinColumn(name = "categoria_restaurante_id")
	)	
	@Size(min = 1, message = "O restaurante precisa ter pelo menos uma categoria")
	@ToString.Exclude
	private Set<CategoriaRestaurante> categorias =  new HashSet<>(0);
	
	@OneToMany(mappedBy = "restaurante")
	private Set<ItemCardapio> itensCardapio = new HashSet<>(0);
		
	public void setLogotipoFileName() {
		
		if (getId() == null) {
			throw new IllegalStateException("É preciso primeiro gravar o registro");
		}		
		
		this.logotipo = String.format("%4d-logo.%s", getId(), FileType.of(logotipoFile.getContentType()).getExtension());
		
	}
	

}
