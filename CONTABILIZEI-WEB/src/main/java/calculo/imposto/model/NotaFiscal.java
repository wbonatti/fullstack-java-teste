package calculo.imposto.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import calculo.imposto.enums.Anexo;

/**
 * Entidade que representa uma nota fiscal
 * 
 * @author wbonatti
 *
 */
@Entity
public class NotaFiscal {

	/**
	 * id - long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * numeroNota - Long
	 */
	private Long numeroNota;

	/**
	 * dataEmissao - {@link Date}
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date dataEmissao;

	/**
	 * descricao - String
	 */
	private String descricao;

	/**
	 * valor - Double
	 */
	private Double valor;

	/**
	 * anexo - {@link Anexo}
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50, nullable = false)
	private Anexo anexo;

	/**
	 * cliente - {@link Cliente}
	 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

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
	 * @return the numeroNota
	 */
	public Long getNumeroNota() {
		return numeroNota;
	}

	/**
	 * @param numeroNota
	 *            the numeroNota to set
	 */
	public void setNumeroNota(Long numeroNota) {
		this.numeroNota = numeroNota;
	}

	/**
	 * @return the dataEmissao
	 */
	public Date getDataEmissao() {
		return dataEmissao;
	}

	/**
	 * @param dataEmissao
	 *            the dataEmissao to set
	 */
	public void setDataEmissao(Date dataEmissao) {
		this.dataEmissao = dataEmissao;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return the valor
	 */
	public Double getValor() {
		return valor;
	}

	/**
	 * @param valor
	 *            the valor to set
	 */
	public void setValor(Double valor) {
		this.valor = valor;
	}

	/**
	 * @return the anexo
	 */
	public Anexo getAnexo() {
		return anexo;
	}

	/**
	 * @param anexo
	 *            the anexo to set
	 */
	public void setAnexo(Anexo anexo) {
		this.anexo = anexo;
	}

	/**
	 * @return the cliente
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * @param cliente
	 *            the cliente to set
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NotaFiscal [numeroNota=" + numeroNota + ", dataEmissao=" + dataEmissao + ", descricao=" + descricao
				+ ", valor=" + valor + ", anexo=" + anexo + "]";
	}

}
