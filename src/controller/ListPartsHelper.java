package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.ListParts;

/**
 * @author Ben Sanders - bsanders3
 * CIS 175 Fall 2021
 */
public class ListPartsHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("Week3Assignment");
	
	public void insertPrice(ListParts li) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);
		em.getTransaction().commit();
		em.close();
	}
	
	public List<ListParts> showAllPrices(){
		EntityManager em = emfactory.createEntityManager();
		List<ListParts> allPrices = em.createQuery("SELECT i FROM ListParts i").getResultList();
		return allPrices;
	}
	public void deletePrice(ListParts toDelete) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListParts> typedQuery = em.createQuery("select li from ListParts li where li.part = :selectedPart and li.price = :selectedPrice", ListParts.class);
		
		typedQuery.setParameter("selectedPart", toDelete.getPart());
		typedQuery.setParameter("selectedPrice", toDelete.getPrice());
		
		typedQuery.setMaxResults(1);
		
		ListParts result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param idToEdit
	 * @return
	 */
	public ListParts searchForPriceById(int idToEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		ListParts found = em.find(ListParts.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * @param toEdit
	 */
	public void updatePrice(ListParts toEdit) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * @param partName
	 * @return
	 */
	public List<ListParts> serchForPriceByPart(String partName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListParts> typedQuery = em.createQuery("select li from ListParts li where li.part = :selectedPart", ListParts.class);
		typedQuery.setParameter("selectedPart", partName);
		
		List<ListParts> foundPrices = typedQuery.getResultList();
		em.close();
		
		return foundPrices;
	}

	/**
	 * @param priceName
	 * @return
	 */
	public List<ListParts> searchForPriceByPrice(String priceName) {
		// TODO Auto-generated method stub
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<ListParts> typedQuery = em.createQuery("select li from ListParts li where li.price = :selectedPrice", ListParts.class);
		typedQuery.setParameter("selectedPrice", priceName);
		
		List<ListParts> foundPrices = typedQuery.getResultList();
		em.close();
		return foundPrices;
	}
	public void cleanUp() {
		emfactory.close();
	}
}
