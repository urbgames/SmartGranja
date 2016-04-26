package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class RelatorioDiarioDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sensoriamento");
	EntityManager manager = factory.createEntityManager();


	public void inserirRelatorio(RelatorioDiario relatorio) {

		manager.getTransaction().begin();
		manager.persist(relatorio);
		manager.getTransaction().commit();
	}

	public RelatorioDiario getById(int id) {

		return manager.find(RelatorioDiario.class, id);
	}

	public void finalizar(){

		manager.close();
		factory.close();

	}


}
