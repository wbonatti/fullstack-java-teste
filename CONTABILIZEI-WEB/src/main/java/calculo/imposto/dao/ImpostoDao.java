package calculo.imposto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import calculo.imposto.model.Imposto;

/**
 * Classe responsavel por fazer a conexão e persistir {@link Imposto}
 * 
 * @author wbonatti
 *
 */
public class ImpostoDao {

	/**
	 * Imposto - String
	 */
	private final String IMPOSTO = "imposto";

	/**
	 * Salvar um {@link Imposto}
	 * 
	 * @param imposto
	 *            {@link Imposto}
	 * 
	 * @return imposto {@link Imposto}
	 */
	public Imposto salva(Imposto imposto) {

		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(imposto);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}

		return imposto;

	}

	/**
	 * Remove um {@link Imposto}
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		EntityManager manager = createEntityManager();

		try {

			manager.getTransaction().begin();
			Imposto imposto = manager.find(Imposto.class, id);

			manager.remove(imposto);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

	}

	/**
	 * Atualiza um {@link Imposto}
	 * 
	 * @param imposto
	 *            {@link Imposto}
	 * 
	 * @return imposto {@link Imposto}
	 */
	public Imposto atualizar(Imposto imposto) {
		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(imposto);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return imposto;

	}

	/**
	 * Pagar um {@link Imposto}
	 * 
	 * @param id
	 * 
	 */
	public void pagarImposto(long id) {
		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();

			// buscando um imposto para setar como pago
			Imposto imposto = manager.find(Imposto.class, id);
			imposto.setPago(true);

			// atualizando a base com o imposto pago
			manager.merge(imposto);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

	}

	/**
	 * Busca um {@link Imposto} por id
	 * 
	 * @param id
	 * @return {@link Imposto}
	 */
	public Imposto buscar(long id) {
		EntityManager manager = createEntityManager();
		Imposto imposto = null;

		try {
			manager.getTransaction().begin();

			// buscando um imposto
			imposto = manager.find(Imposto.class, id);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return imposto;

	}

	/**
	 * Buscando {@link Imposto} cadastrado por ano mes de referencia
	 * 
	 * @param id
	 * 
	 * @return List off imposto {@link Imposto}
	 */
	@SuppressWarnings("unchecked")
	public List<Imposto> buscaPorMesAnoReferencia(String anoMes) {
		EntityManager manager = createEntityManager();
		List<Imposto> impostos = null;

		try {
			manager.getTransaction().begin();

			Query query = manager.createQuery("select imp from Imposto imp where imp.mesAnoReferencia = :mesAno");
			query.setParameter("mesAno", anoMes);

			impostos = query.getResultList();

			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return impostos;

	}

	/**
	 * Buscando {@link Imposto} cadastrados por cliente
	 * 
	 * @return List off imposto {@link Imposto}
	 */
	@SuppressWarnings("unchecked")
	public List<Imposto> buscaPorCliente(long id) {
		EntityManager manager = createEntityManager();
		List<Imposto> impostos = null;

		try {
			manager.getTransaction().begin();

			Query query = manager.createQuery("select imp from Imposto imp where imp.cliente_id = :cliente");
			query.setParameter("cliente", id);

			impostos = query.getResultList();
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return impostos;

	}

	/**
	 * Metodo responsavel por criar uma entity manager
	 * 
	 * @return {@link EntityManager}
	 */
	private EntityManager createEntityManager() {
		EntityManager manager = null;

		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(IMPOSTO);
			manager = factory.createEntityManager();

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return manager;
	}
}
