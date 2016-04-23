package projetoGranja2;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SensoriamentoDAO {
	
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sensoriamento");
	EntityManager manager = factory.createEntityManager();
	
	public void inserirSensoriamento(SensoriamentoVO sensoriamento) {
		
		manager.getTransaction().begin();
		manager.persist(sensoriamento);
		manager.getTransaction().commit();
		
	}
	
	public void finalizar(){
		
		manager.close();
		factory.close();
		
	}
	
	public static void main(String[] args) {
	}

}
