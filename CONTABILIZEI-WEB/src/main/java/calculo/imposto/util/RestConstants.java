package calculo.imposto.util;

/**
 * Interface com Caminho das chamadas Rest
 * 
 * @author wbonatti
 *
 */
public interface RestConstants {

	/**
	 * CLIENTE_LISTAR_TODOS - String
	 */
	public static final String CLIENTE_LISTAR_TODOS = "/listarTodos";

	/**
	 * CLIENTE_LISTAR_ID - String
	 */
	public static final String CLIENTE_LISTAR_ID = "/listar/{id}";

	/**
	 * CLIENTE_SALVAR - String
	 */
	public static final String CLIENTE_SALVAR = "/salvar";

	/**
	 * CLIENTE_DELETAR_ID - String
	 */
	public static final String CLIENTE_DELETAR_ID = "/deletar/{id}";

	/**
	 * CLIENTE_ATUALIZAR - String
	 */
	public static final String CLIENTE_ATUALIZAR = "/atualizar";

	/**
	 * IMPOSTO_LISTAR_POR_CLIENTE - String
	 */
	public static final String IMPOSTO_LISTAR_POR_CLIENTE = "/listarPorCliente/{id}";

	/**
	 * IMPOSTO_LISTAR_POR_ANO_MES - String
	 */
	public static final String IMPOSTO_LISTAR_POR_ANO_MES = "/listarPorAnoMes/{anoMes}";

	/**
	 * IMPOSTO_CALCULAR - String
	 */
	public static final String IMPOSTO_CALCULAR = "/calcular/{id}";

	/**
	 * IMPOSTO_PAGAR - String
	 */
	public static final String IMPOSTO_PAGAR = "/pagar/{id}";

	/**
	 * NOTA_FICAL_LISTAR_POR_ANO_MES - String
	 */
	public static final String NOTA_FICAL_LISTAR_POR_ANO_MES = "/listarPorAnoMes/{anoMes}";

	/**
	 * NOTA_FICAL_LISTAR_POR_CLIENTE - String
	 */
	public static final String NOTA_FICAL_LISTAR_POR_CLIENTE = "/listarPorCliente/{id}";

	/**
	 * NOTA_FICAL_SALVAR - String
	 */
	public static final String NOTA_FICAL_SALVAR = "/salvar";

}
