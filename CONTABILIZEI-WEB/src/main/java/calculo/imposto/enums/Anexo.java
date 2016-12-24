package calculo.imposto.enums;

/**
 * Enum representando um anexo da empresa
 * 
 * @author wbonatti
 *
 */
public enum Anexo {

	COMERCIO(1, 0.06), INDUSTRIA(2, 0.085), PRESTACAO_DE_SERVICOS(3, 0.11);

	/**
	 * id - int
	 */
	private int id;

	/**
	 * value - double
	 */
	private double value;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the value
	 */
	public double getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(double value) {
		this.value = value;
	}

	/**
	 * @param id
	 * @param value
	 */
	private Anexo(int id, double value) {
		this.id = id;
		this.value = value;
	}

}
