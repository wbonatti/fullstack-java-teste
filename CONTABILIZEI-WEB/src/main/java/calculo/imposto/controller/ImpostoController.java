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

			Calendar data = Calendar.getInstance();

			if (cliente.getRegimeTributario().equals(RegimeTributario.SIMPLES_NACIONAL)) {
				Imposto imposto = new Imposto();
				imposto.setCliente(cliente);
				imposto.setMesAnoReferencia(data.get(Calendar.MONTH) + "/" + data.get(Calendar.YEAR));
				imposto.setTipoImposto(TipoImposto.SIMPLES_NACIONAL);
				imposto.setValor(cliente.getNotasFiscaisCalculoSimples());
				imposto.setVencimento(data.getTime());
				getDao().salva(imposto);

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

}
