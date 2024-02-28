package dao;


import java.util.List;

import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import model.Document;
import model.Person;


@Stateless
public class UniversalDAO {

    @PersistenceContext
    private EntityManager em;

    //******************************************************************************************
    //documents
    


    public void insert1(Document Document1) {
    	
    	em.persist(Document1);
    	
    }

    public void update1(Document Document1) {
    	
    	
    	em.merge(Document1);
    	
    }
    
    public void delete1(Document Document1) {
    	
    	Document1 = load1(Document1.getId());
    	em.remove(Document1);
    	
    }

    public Document load1(Integer id) {
    	
    	
    	
    	return em.find(Document.class, id);
    }

    public List<Document> findAll(){
    	
    	return em.createQuery("Select e from Document e" , Document.class).getResultList();
    }
    
    public List<Document> findDocumentsByPersonId1(Integer personId) {
        return em.createQuery(
            "SELECT d FROM Document d WHERE d.person.id = :personId", Document.class)
            .setParameter("personId", personId)
            .getResultList();
    }
    

    
    
    
    //*******************************************************************************************
    
    
    
    
    
    
    
    
    
    
    
    
    // Person methods
    public void insertPerson(Person person) {
        em.persist(person);
    }

    public void updatePerson(Person person) {
        em.merge(person);
    }
    public Person findPersonById(Integer personId) {
        return em.find(Person.class, personId);
    }
    public Document findDocumentById(Document editDocumentId) {
        return em.find(Document.class, editDocumentId);
    }
    
    public Integer findLastPersonId() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Integer> cq = cb.createQuery(Integer.class);
        Root<Person> root = cq.from(Person.class);
        cq.select(cb.max(root.get("id")));
        return em.createQuery(cq).getSingleResult();
    }
    public void deletePerson(Person person) {
        person = loadPerson(person.getId());
        em.remove(person);
    }

    public Person loadPerson(Integer l) {
        return em.find(Person.class, l);
    }

    public List<Person> findAllPersons() {
        return em.createQuery("SELECT p FROM Person p", Person.class).getResultList();
    }

    // Document methods
    
    public void update(Document Document) {
    	
    	em.merge(Document);
    	
    }
    
    
    public void insertDocument(Document document) {
        em.persist(document);
    }

    public void updateDocument(Document document) {
        if (document != null) {
            System.out.println("Updating document: " + document.getId());
            em.merge(document);
        } else {
            System.out.println("Document is null. Cannot update.");
        }
    }
    public void deleteDocument(Document document) {
        document = loadDocument(document.getId());
        em.remove(document);
    }
    
    

    public Document loadDocument(Integer id) {
        return em.find(Document.class, id);
    }

    public List<Document> findAllDocuments() {
        return em.createQuery("SELECT d FROM Document d", Document.class).getResultList();
    }

    public List<Document> findDocumentsByPersonId(Integer personId) {
        return em.createQuery(
            "SELECT d FROM Document d WHERE d.person.id = :personId", Document.class)
            .setParameter("personId", personId)
            .getResultList();
    }
    
    
    
}
