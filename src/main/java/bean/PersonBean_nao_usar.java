package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dao.PersonEJB_nao_usar;
import model.Person;

@Named
@RequestScoped
public class PersonBean_nao_usar implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private PersonEJB_nao_usar personEJB;
	
	private Person person;
	
	private Integer idPesquisar;
	
	private List<Person> persons;
	
	@PostConstruct
	public void init() {
		persons = personEJB.findAll();
	}
	
	public String inserir() {
		
		personEJB.insert(person);
		
		 return null;
		 
	}
	
	public String alterar() {
		
		personEJB.update(person);
		return null;
	}
	
    public String apagar() {
		
		personEJB.delete(person);
		return null;
	}

    public String novo() {
		
		person = new Person();
		return null;
	}
    
    
   public String buscaId() {
	   	
	   	person = personEJB.load(idPesquisar);
		return null;
	}
    
    
    
    
	
	
	public Person getPerson() {
		
		if(person == null) {
			person = new Person();
		}
		return person;
	}

	public Integer getIdPesquisar() {
		return idPesquisar;
	}

	public void setIdPesquisar(Integer idPesquisar) {
		this.idPesquisar = idPesquisar;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	
    
}
