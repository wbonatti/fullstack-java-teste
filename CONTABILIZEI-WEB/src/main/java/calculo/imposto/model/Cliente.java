package calculo.imposto.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import calculo.imposto.enums.Anexo;
import calculo.imposto.enums.RegimeTributario;

/**
 * Entidade representando um cliente
 * 
 * @author wbonatti
 *
 */
@Entity
public class Cliente {

	/**
	 * id - long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * razaoSocial - String
	 */
	@Column(nullable = false)
	private String razaoSocial;

	/**
	 * cnpj - Long
	 */
	private Long cnpj;

	/**
	 * regimeTributario - {@link RegimeTributario}
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50, nullable = false)
	private RegimeTributario regimeTributario;

	/**
	 * anexo - Long
	 */
	private Long anexo;

	/**
	 * email - String
	 */
	@Column(length = 50, nullable = false)
	private String email;

	/**
	 * notasFiscais - list off {@link NotaFiscal}
	 */
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<NotaFiscal> notasFiscais;

	/**
	 * impostos - list off {@link Imposto}
	 */
	@OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Imposto> impostos;

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the razaoSocial
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * @param razaoSocial
	 *            the razaoSocial to set
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * @return the cnpj
	 */
	public Long getCnpj() {
		return cnpj;
	}

	/**
	 * @param cnpj
	 *            the cnpj to set
	 */
	public void setCnpj(Long cnpj) {
		this.cnpj = cnpj;
	}

	/**
	 * @return the regimeTributario
	 */
	public RegimeTributario getRegimeTributario() {
		return regimeTributario;
	}

	/**
	 * @param regimeTributario
	 *            the regimeTributario to set
	 */
	public void setRegimeTributario(RegimeTributario regimeTributario) {
		this.regimeTributario = regimeTributario;
	}

	/**
	 * @return the anexo
	 */
	public Long getAnexo() {
		return anexo;
	}

	/**
	 * @param anexo
	 *            the anexo to set
	 */
	public void setAnexo(Long anexo) {
		this.anexo = anexo;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the notasFiscais
	 */
	public List<NotaFiscal> getNotasFiscais() {
		return notasFiscais;
	}

	/**
	 * Calcula o valor das notas fiscais pelo simples
	 * 
	 * @return Double
	 * 
	 */
	public Double getNotasFiscaisCalculoSimples() {
		double valor = 0;

		for (NotaFiscal nota : this.notasFiscais) {
			if (nota.getAnexo().equals(Anexo.COMERCIO)) {
				valor += nota.getValor() * Anexo.COMERCIO.getValue();
			}
			if (nota.getAnexo().equals(Anexo.INDUSTRIA)) {
				valor += nota.getValor() * Anexo.INDUSTRIA.getValue();
			}
			if (nota.getAnexo().equals(Anexo.PRESTACAO_DE_SERVICOS)) {
				valor += nota.getValor() * Anexo.PRESTACAO_DE_SERVICOS.getValue();
			}
		}

		return valor;
	}

	/**
	 * @param notasFiscais
	 *            the notasFiscais to set
	 */
	public void setNotasFiscais(List<NotaFiscal> notasFiscais) {
		this.notasFiscais = notasFiscais;
	}

	/**
	 * @return the impostos
	 */
	public List<Imposto> getImpostos() {
		return impostos;
	}

	/**
	 * @param impostos
	 *            the impostos to set
	 */
	public void setImpostos(List<Imposto> impostos) {
		this.impostos = impostos;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Cliente [razaoSocial=" + razaoSocial + ", cnpj=" + cnpj + ", regimeTributario=" + regimeTributario
				+ ", anexo=" + anexo + ", email=" + email + "]";
	}

}
