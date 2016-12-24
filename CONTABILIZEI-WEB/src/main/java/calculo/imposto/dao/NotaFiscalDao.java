package calculo.imposto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import calculo.imposto.model.Cliente;

/**
 * Classe responsavel por fazer a conexão e persistir {@link Cliente}
 * 
 * @author wbonatti
 *
 */
public class NotaFiscalDao {

	/**
	 * Cliente - cliente
	 */
	private final String CLIENTE = "cliente";

	/**
	 * Salvar um {@link Cliente}
	 * 
	 * @param cliente
	 *            {@link Cliente}
	 * 
	 * @return cliente {@link Cliente}
	 */
	public Cliente salva(Cliente cliente) {

		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(cliente);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}

		return cliente;

	}

	/**
	 * Remove um {@link Cliente}
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		EntityManager manager = createEntityManager();

		try {

			manager.getTransaction().begin();
			Cliente cliente = manager.find(Cliente.class, id);

			manager.remove(cliente);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

	}

	/**
	 * Atualiza um {@link Cliente}
	 * 
	 * @param cliente
	 *            {@link Cliente}
	 * 
	 * @return cliente {@link Cliente}
	 */
	public Cliente atualizar(Cliente cliente) {
		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(cliente);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return cliente;

	}

	/**
	 * Buscando {@link Cliente} cadastrado
	 * 
	 * @param id
	 * 
	 * @return cliente {@link Cliente}
	 */
	public Cliente busca(long id) {
		EntityManager manager = createEntityManager();
		Cliente cliente = null;

		try {
			manager.getTransaction().begin();
			cliente = manager.find(Cliente.class, id);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return cliente;

	}

	/**
	 * Buscando {@link Cliente} cadastrados
	 * 
	 * @return List off cliente {@link Cliente}
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> buscaTodos() {
		EntityManager manager = createEntityManager();
		List<Cliente> clientes = null;

		try {
			manager.getTransaction().begin();
			clientes = manager.createQuery("SELECT cli FROM Cliente cli").getResultList();
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return clientes;

	}

	/**
	 * Metodo responsavel por criar uma entity manager
	 * 
	 * @return {@link EntityManager}
	 */
	private EntityManager createEntityManager() {
		EntityManager manager = null;

		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(CLIENTE);
			manager = factory.createEntityManager();

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return manager;
	}
}
