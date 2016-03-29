package soa.backend.layer.entreprise.ejb;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import soa.backend.layer.entreprise.jpa.Category;
import soa.backend.layer.entreprise.jpa.Item;

/**
 * Session Bean implementation class SOAEjb
 */
@Stateless
@LocalBean
public class SOAEjb {
	
	@PersistenceContext
	private EntityManager entityManager;
	
    public Category createCateg(String name){
    	Category categ = new Category();
    	categ.setName(name);
    	categ.setItems(new ArrayList<Item>());
    	
    	try{
    		entityManager.persist(categ);
    		return categ;
    	}catch(Exception e){
    		return null;
    	}
    }
    
	public List<Category> getCategs(){
    	TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
    	List<Category> results = query.getResultList();    	
    	return results;
    }

}
