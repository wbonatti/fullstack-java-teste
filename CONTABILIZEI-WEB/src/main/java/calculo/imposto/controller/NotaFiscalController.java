package calculo.imposto.controller;

import java.util.List;

import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import calculo.imposto.dao.NotaFiscalDao;
import calculo.imposto.model.Cliente;
import calculo.imposto.model.NotaFiscal;
import calculo.imposto.util.RestConstants;

/**
 * Controller responsável por executar ações da nota fiscal
 * 
 * @author wbonatti
 *
 */
@Path("notaFiscal")
public class NotaFiscalController implements RestConstants {

	/**
	 * Ano mes - String
	 */
	private static final String ANO_MES = "anoMes";

	/**
	 * Id - String
	 */
	private static final String ID = "id";

	/**
	 * Metodo responsavel por buscar todas as {@link NotaFiscal} do
	 * {@link Cliente}
	 * 
	 * @param id
	 * @return List off {@link NotaFiscal} {@link Json}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(NOTA_FICAL_LISTAR_POR_CLIENTE)
	public List<NotaFiscal> buscandoNotaFiscalCliente(@PathParam(ID) long id) {

		List<NotaFiscal> notasFiscais = null;

		try {
			// buscando uma nota pelo cliente
			notasFiscais = getDao().buscaPorCliente(id);

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return notasFiscais;
	}

	/**
	 * Metodo responsavel por buscar todas as {@link NotaFiscal} do
	 * {@link Cliente} pelo ano e mes de referencia
	 * 
	 * @param id
	 * @return List off {@link NotaFiscal} {@link Json}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(NOTA_FICAL_LISTAR_POR_ANO_MES)
	public List<NotaFiscal> buscandoNotaFiscalAnoMes(@PathParam(ANO_MES) String anoMes) {

		List<NotaFiscal> notasFiscais = null;

		try {
			// buscando uma nota pelo mes e ano de referencia
			notasFiscais = getDao().buscaPorMesAnoReferencia(anoMes);

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return notasFiscais;
	}

	/**
	 * Metodo responsavel por salvar uma {@link NotaFiscal}
	 * 
	 * @param notaFiscal
	 *            {@link NotaFiscal}
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(NOTA_FICAL_SALVAR)
	public String salvar(NotaFiscal notaFiscal) {

		try {
			// salvando uma nota fiscal
			getDao().atualizar(notaFiscal);
			return "Salvo com sucesso!";

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "Erro ao salvar";
	}

	/**
	 * Instancia uma classe dao
	 * 
	 * @return {@link NotaFiscalDao}
	 */
	private NotaFiscalDao getDao() {
		NotaFiscalDao dao = new NotaFiscalDao();
		return dao;
	}

}
