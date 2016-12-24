package calculo.imposto.enums;

/**
 * Enum responsavel por guardar os tipos de impostos
 * 
 * @author wbonatti
 *
 */
public enum TipoImposto {

	SIMPLES_NACIONAL(0), IMPOSTO_DE_RENDA(4.8), ISS(2), COFINS(3);

	/**
	 * value - double
	 */
	private double value;

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
	 * @param value
	 */
	private TipoImposto(double value) {
		this.value = value;
	}

}
