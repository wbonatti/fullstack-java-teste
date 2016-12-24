package calculo.imposto.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import calculo.imposto.model.NotaFiscal;

/**
 * Classe responsavel por fazer a conexão e persistir {@link NotaFiscal}
 * 
 * @author wbonatti
 *
 */
public class NotaFiscalDao {

	/**
	 * Cliente - cliente
	 */
	private final String NOTA_FISCAL = "notaFiscal";

	/**
	 * Salvar um {@link NotaFiscal}
	 * 
	 * @param nota
	 *            {@link NotaFiscal}
	 * 
	 * @return nota {@link NotaFiscal}
	 */
	public NotaFiscal salva(NotaFiscal notaFiscal) {

		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.persist(notaFiscal);
			manager.getTransaction().commit();
		} finally {
			manager.close();
		}

		return notaFiscal;

	}

	/**
	 * Remove um {@link NotaFiscal}
	 * 
	 * @param id
	 */
	public void remove(Long id) {
		EntityManager manager = createEntityManager();

		try {

			manager.getTransaction().begin();
			NotaFiscal notaFiscal = manager.find(NotaFiscal.class, id);

			manager.remove(notaFiscal);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

	}

	/**
	 * Atualiza um {@link NotaFiscal}
	 * 
	 * @param {@link
	 * 			NotaFiscal}
	 * 
	 * @return {@link NotaFiscal}
	 */
	public NotaFiscal atualizar(NotaFiscal notaFiscal) {
		EntityManager manager = createEntityManager();

		try {
			manager.getTransaction().begin();
			manager.merge(notaFiscal);
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return notaFiscal;

	}

	/**
	 * Buscando {@link NotaFiscal} cadastrado por ano mes de referencia
	 * 
	 * @param id
	 * 
	 * @return List off nota {@link NotaFiscal}
	 */
	@SuppressWarnings("unchecked")
	public List<NotaFiscal> buscaPorMesAnoReferencia(String anoMes) {
		EntityManager manager = createEntityManager();
		List<NotaFiscal> notasFiscais = null;

		try {
			manager.getTransaction().begin();

			Query query = manager.createQuery("select nota from NotaFiscal nota where nota.mesAnoReferencia = :mesAno");
			query.setParameter("mesAno", anoMes);

			notasFiscais = query.getResultList();

			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return notasFiscais;

	}

	/**
	 * Buscando {@link NotaFiscal} cadastrados por cliente
	 * 
	 * @return List off {@link NotaFiscal}
	 */
	@SuppressWarnings("unchecked")
	public List<NotaFiscal> buscaPorCliente(long id) {
		EntityManager manager = createEntityManager();
		List<NotaFiscal> notasFiscais = null;

		try {
			manager.getTransaction().begin();

			Query query = manager.createQuery("select nota from NotaFiscal nota where nota.cliente_id = :cliente");
			query.setParameter("cliente", id);

			notasFiscais = query.getResultList();
			manager.getTransaction().commit();

		} finally {
			manager.close();
		}

		return notasFiscais;

	}

	/**
	 * Metodo responsavel por criar uma entity manager
	 * 
	 * @return {@link EntityManager}
	 */
	private EntityManager createEntityManager() {
		EntityManager manager = null;

		try {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory(NOTA_FISCAL);
			manager = factory.createEntityManager();

		} catch (Exception exception) {
			System.err.println(exception.getMessage());
			exception.printStackTrace();
		}

		return manager;
	}
}
