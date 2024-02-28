package dao;

import java.util.List;

import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Person;


@Stateful
public class PersonEJB {

    @PersistenceContext
    private EntityManager em;
    
    

    public void insert(Person person) {
    	 em.persist(person); 
    	
         
          
          }

    public void update(Person person) {
    	
    	em.merge(person);
    }

    public void delete(Person person) {
        person = load(person.getId());
        em.remove(person);
    }

    public Person load(Integer id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAll() {
        
        return em.createQuery("SELECT p FROM Person p",Person.class).getResultList();
    }

}

