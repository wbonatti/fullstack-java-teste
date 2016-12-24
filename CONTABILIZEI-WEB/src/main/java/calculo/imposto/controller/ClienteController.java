package calculo.imposto.controller;

import java.util.List;

import javax.json.Json;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import calculo.imposto.dao.ClienteDao;
import calculo.imposto.model.Cliente;
import calculo.imposto.util.RestConstants;

/**
 * Controller responsável por executar ações do usuário
 * 
 * @author wbonatti
 *
 */
@Path("cliente")
public class ClienteController implements RestConstants {

	/**
	 * Id - String
	 */
	private static final String ID = "id";

	/**
	 * Metodo responsavel por buscar os clientes cadastrados
	 * 
	 * @return List of {@link Cliente} {@link Json}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CLIENTE_LISTAR_TODOS)
	public List<Cliente> buscandoClientes() {

		List<Cliente> clientes = null;

		try {
			// buscando da base de dados todos os clientes cadastrados
			clientes = getDao().buscaTodos();

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return clientes;

	}

	/**
	 * Metodo responsavel por buscar um unico cliente
	 * 
	 * @param id
	 * @return {@link Cliente} {@link Json}
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CLIENTE_LISTAR_ID)
	public Cliente buscandoCliente(@PathParam(ID) long id) {

		Cliente cliente = null;

		try {
			// buscando um cliente pelo id
			cliente = getDao().busca(id);

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return cliente;
	}

	/**
	 * Metodo responsavel por salvar um cliente
	 * 
	 * @param cliente
	 *            {@link Cliente}
	 * @return
	 */
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(CLIENTE_SALVAR)
	public String salvandoCliente(Cliente cliente) {

		try {
			// salvando um cliente
			getDao().salva(cliente);
			return "salvo com sucesso!";

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "Erro ao salvar";
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path(CLIENTE_DELETAR_ID)
	public String deletandoCliente(@PathParam(ID) long id) {

		try {
			// removendo um cliente
			getDao().remove(id);
			return "Removido com sucesso!";

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "Erro ao remover";
	}

	/**
	 * Metodo responsavel por atualizar um cliente
	 * 
	 * @param cliente
	 *            {@link Cliente}
	 * @return
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	@Path(CLIENTE_ATUALIZAR)
	public String atualizandoCliente(Cliente cliente) {

		try {
			// salvando um cliente
			getDao().atualizar(cliente);
			return "Atualizado com sucesso!";

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return "Erro ao atualizar";
	}

	/**
	 * Instancia uma classe dao
	 * 
	 * @return {@link ClienteDao}
	 */
	private ClienteDao getDao() {
		ClienteDao dao = new ClienteDao();
		return dao;
	}

}
