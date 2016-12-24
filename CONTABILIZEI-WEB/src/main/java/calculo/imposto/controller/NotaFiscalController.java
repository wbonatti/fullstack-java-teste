package calculo.imposto.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import calculo.imposto.enums.Anexo;
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

		List<NotaFiscal> imp = new ArrayList<NotaFiscal>();

		System.out.println(id);
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setAnexo(Anexo.COMERCIO);
		notaFiscal.setCliente(new Cliente());
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setDescricao("descricao");
		notaFiscal.setNumeroNota(123l);
		notaFiscal.setValor(1235.4);

		imp.add(notaFiscal);

		return imp;
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

		List<NotaFiscal> imp = new ArrayList<NotaFiscal>();

		System.out.println(anoMes);
		NotaFiscal notaFiscal = new NotaFiscal();
		notaFiscal.setAnexo(Anexo.COMERCIO);
		notaFiscal.setCliente(new Cliente());
		notaFiscal.setDataEmissao(new Date());
		notaFiscal.setDescricao("descricao");
		notaFiscal.setNumeroNota(123l);
		notaFiscal.setValor(1235.4);

		imp.add(notaFiscal);

		return imp;
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

		return "Salvo";
	}

}
