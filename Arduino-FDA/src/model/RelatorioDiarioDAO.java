package model;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class RelatorioDiarioDAO {

	EntityManagerFactory factory = Persistence.createEntityManagerFactory("sensoriamento");
	EntityManager manager = factory.createEntityManager();

	public void atualizarRelatorio(RelatorioDiario relatorio){
		
		manager.getTransaction().begin();
		RelatorioDiario relatorioDiario = manager.find(RelatorioDiario.class, relatorio.getId());
		relatorioDiario.setMortalidade(relatorio.getMortalidade());
		manager.getTransaction().commit();
		
	}

	public void inserirRelatorio(RelatorioDiario relatorio) {

		manager.getTransaction().begin();
		manager.persist(relatorio);
		manager.getTransaction().commit();
	}

	public RelatorioDiario getById(int id) {

		return manager.find(RelatorioDiario.class, id);

	}

	public List<RelatorioDiario> listarRelatorioDiario() {
		
		Query query = manager.createQuery("from RelatorioDiario");
		List<RelatorioDiario> lista =  query.getResultList();
		
		return lista;
	}
	
	public Vector<RelatorioDiario> listarRelatorioDiarioVector() {
		
		Query query = manager.createQuery("from RelatorioDiario");
		List<RelatorioDiario> lista =  query.getResultList();
		
		Vector<RelatorioDiario> vectoRelatorio = new Vector<RelatorioDiario>();
		for (RelatorioDiario relatorioDiario : lista) {
			
			vectoRelatorio.addElement(relatorioDiario);
		}
		
		return vectoRelatorio;
	}

	public void finalizar(){

		manager.close();
		factory.close();

	}


}
