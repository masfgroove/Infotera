package dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Document;

@Stateful
public class DocumentEJB_nao_usar {
	    
		@PersistenceContext
		private EntityManager em;
		
		

	    public void insert(Document Document) {
	    	
	    	em.persist(Document);
	    	
	    }
	
        public void update(Document Document) {
        
        	em.merge(Document);
	    	
	    }
        
        public void delete(Document Document) {
        	
        	Document = load(Document.getId());
        	em.remove(Document);
	    	
	    }
	
        public Document load(Integer id) {
	    	
        	
        	
        	return em.find(Document.class, id);
	    }

        public List<Document> findAll(){
        	
        	return em.createQuery("Select e from Document e" , Document.class).getResultList();
        }
        
        public List<Document> findDocumentsByPersonId(Integer personId) {
            return em.createQuery(
                "SELECT d FROM Document d WHERE d.person.id = :personId", Document.class)
                .setParameter("personId", personId)
                .getResultList();
        }
        
}

