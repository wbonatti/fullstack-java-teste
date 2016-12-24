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

import calculo.imposto.enums.TipoImposto;

/**
 * Entidade que representa um imposto
 * 
 * @author wbonatti
 *
 */
@Entity
public class Imposto {

	/**
	 * id - long
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	/**
	 * tipoImposto - {@link TipoImposto}
	 */
	@Enumerated(EnumType.STRING)
	@Column(length = 50, nullable = false)
	private TipoImposto tipoImposto;

	/**
	 * vencimento - Date
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date vencimento;

	/**
	 * valor - Double
	 */
	private Double valor;

	/**
	 * mesAnoReferencia - String
	 */
	private String mesAnoReferencia;

	/**
	 * pago - boolean
	 */
	private boolean pago;

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
	 * @return the tipoImposto
	 */
	public TipoImposto getTipoImposto() {
		return tipoImposto;
	}

	/**
	 * @param tipoImposto
	 *            the tipoImposto to set
	 */
	public void setTipoImposto(TipoImposto tipoImposto) {
		this.tipoImposto = tipoImposto;
	}

	/**
	 * @return the vencimento
	 */
	public Date getVencimento() {
		return vencimento;
	}

	/**
	 * @param vencimento
	 *            the vencimento to set
	 */
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
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
	 * @return the mesAnoReferencia
	 */
	public String getMesAnoReferencia() {
		return mesAnoReferencia;
	}

	/**
	 * @param mesAnoReferencia
	 *            the mesAnoReferencia to set
	 */
	public void setMesAnoReferencia(String mesAnoReferencia) {
		this.mesAnoReferencia = mesAnoReferencia;
	}

	/**
	 * @return the pago
	 */
	public boolean isPago() {
		return pago;
	}

	/**
	 * @param pago
	 *            the pago to set
	 */
	public void setPago(boolean pago) {
		this.pago = pago;
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
		return "Imposto [tipoImposto=" + tipoImposto + ", vencimento=" + vencimento + ", valor=" + valor
				+ ", mesAnoReferencia=" + mesAnoReferencia + ", pago=" + pago + "]";
	}

}
