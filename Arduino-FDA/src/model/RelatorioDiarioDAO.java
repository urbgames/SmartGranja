package model;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


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

	public Vector<String> listarDatas() {
		
		Query query = manager.createQuery("from RelatorioDiario");
		List<RelatorioDiario> lista =  query.getResultList();
		Vector<String> vectorDatas = new Vector<String>();

		for (RelatorioDiario relatorio : lista) {
			vectorDatas.add(relatorio.getData());			
		}

		return vectorDatas;
	}

	public void finalizar(){

		manager.close();
		factory.close();

	}


}
