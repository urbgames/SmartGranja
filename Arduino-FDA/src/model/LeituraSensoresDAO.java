package model;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class LeituraSensoresDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sensoriamento");
	EntityManager manager = factory.createEntityManager();
	
	public void inserirLeitura(LeituraSensores leitura) {
		
		manager.getTransaction().begin();
		manager.persist(leitura);
		manager.getTransaction().commit();
		
	}
	
	public void finalizar(){
		
		manager.close();
		factory.close();
		
	}
}
