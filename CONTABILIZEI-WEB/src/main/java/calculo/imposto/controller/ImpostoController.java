package calculo.imposto.controller;

import java.util.Calendar;
import java.util.List;

import javax.json.Json;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import calculo.imposto.dao.ClienteDao;
import calculo.imposto.dao.ImpostoDao;
import calculo.imposto.enums.RegimeTributario;
import calculo.imposto.enums.TipoImposto;
import calculo.imposto.model.Cliente;
import calculo.imposto.model.Imposto;
import calculo.imposto.model.NotaFiscal;
import calculo.imposto.util.RestConstants;

/**
 * Controller responsável por executar ações do imposto
 * 
 * @author wbonatti
 *
 */
@Path("imposto")
public class ImpostoController implements RestConstants {

	/**
	 * Ano mes - String
	 */
	private static final String ANO_MES = "anoMes";

	/**
	 * Id - String
	 */
	private static final String ID = "id";

	/**
	 * Metodo responsavel por buscar {@link Imposto} pelo {@link Cliente}
	 * 
	 * @param id
	 * @return List off {@link Imposto} {@link Json}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(IMPOSTO_LISTAR_POR_CLIENTE)
	public List<Imposto> buscandoImpostoCliente(@PathParam(ID) long id) {

		List<Imposto> impostos = null;

		try {
			// buscando da base de dados todos os impostos cadastrados pelo id
			// do cliente
			impostos = getDao().buscaPorCliente(id);

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return impostos;

	}

	/**
	 * Metodo responsavel por buscar {@link Imposto} pelo {@link Cliente}
	 * atraves do ano e mes de referencia
	 * 
	 * @param anoMes
	 * @return List off {@link Imposto} {@link Json}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(IMPOSTO_LISTAR_POR_ANO_MES)
	public List<Imposto> buscandoImpostoAnoMes(@PathParam(ANO_MES) String anoMes) {

		List<Imposto> impostos = null;

		try {
			// buscando da base de dados todos os impostos cadastrados pelo mes
			// e ano de referencia
			impostos = getDao().buscaPorMesAnoReferencia(anoMes);

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return impostos;
	}

	/**
	 * Metodo responsavel por calcular o imposto para o {@link Cliente}
	 * 
	 * @param id
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path(IMPOSTO_CALCULAR)
	public String calcularImposto(@PathParam(ID) long id) {

		try {
			// instanciando um dao do cliente para recuperar
			ClienteDao clientedao = new ClienteDao();

			// busca cliente pelo id
			Cliente cliente = clientedao.busca(id);

			if (cliente.getNotasFiscais() == null) {
				return "Sem notas para calcular";
			}

			// pega a data atual
			Calendar data = Calendar.getInstance();

			// verifica se o cliente é um cadastro simples ou um lucro presumido
			if (cliente.getRegimeTributario().equals(RegimeTributario.SIMPLES_NACIONAL)) {
				Imposto imposto = createSimpleImposto(cliente, data);
				getDao().salva(imposto);

			} else {
				// pega o valor total das notas
				Double valor = cliente.getNotasFiscaisCalculoLucroPresumido();

				// salva os impostos
				Imposto impostoConfins = createImposto(cliente, data, valor, TipoImposto.COFINS);
				getDao().salva(impostoConfins);
				Imposto impostoRenda = createImposto(cliente, data, valor, TipoImposto.IMPOSTO_DE_RENDA);
				getDao().salva(impostoRenda);
				Imposto impostoIss = createImposto(cliente, data, valor, TipoImposto.ISS);
				getDao().salva(impostoIss);
			}

			return "Impostos calculados";

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "Erro ao calcular impostos";

	}

	/**
	 * Metodo responsavel por pagar um {@link Imposto}
	 * 
	 * @param id
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path(IMPOSTO_PAGAR)
	public String pagarImposto(@PathParam(ID) long id) {

		try {
			// salvando um cliente
			getDao().pagarImposto(id);
			return "Imposto pago com sucesso!";

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "Erro ao pagar imposto";
	}

	/**
	 * Instancia uma classe dao
	 * 
	 * @return {@link ClienteDao}
	 */
	private ImpostoDao getDao() {
		ImpostoDao dao = new ImpostoDao();
		return dao;
	}

	/**
	 * Metodo que cria um imposto par ao simples nacional
	 * 
	 * @param cliente
	 *            {@link Cliente}
	 * @param data
	 *            {@link Calendar}
	 * @return {@link Imposto}
	 */
	private Imposto createSimpleImposto(Cliente cliente, Calendar data) {
		Imposto imposto = new Imposto();
		imposto.setCliente(cliente);
		imposto.setMesAnoReferencia(data.get(Calendar.MONTH) + "/" + data.get(Calendar.YEAR));
		imposto.setTipoImposto(TipoImposto.SIMPLES_NACIONAL);
		imposto.setValor(cliente.getNotasFiscaisCalculoSimples());
		imposto.setVencimento(data.getTime());
		return imposto;
	}

	/**
	 * Metodo que cria um imposto e retorna
	 * 
	 * @param cliente
	 *            {@link Cliente}
	 * @param data
	 *            {@link Calendar}
	 * @param valor
	 *            {@link Double}
	 * 
	 * @return {@link Imposto}
	 * 
	 */
	private Imposto createImposto(Cliente cliente, Calendar data, Double valor, TipoImposto tipoImposto) {
		Imposto imposto = new Imposto();
		imposto.setCliente(cliente);
		imposto.setMesAnoReferencia(data.get(Calendar.MONTH) + "/" + data.get(Calendar.YEAR));
		imposto.setTipoImposto(tipoImposto);
		imposto.setValor(valor * tipoImposto.getValue());
		imposto.setVencimento(data.getTime());
		return imposto;
	}

}
