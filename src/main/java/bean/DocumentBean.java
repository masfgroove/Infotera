package bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import dao.DocumentEJB;
import model.Document;

@Named
@RequestScoped
public class DocumentBean implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private DocumentEJB documentEJB;
	
	private Document document;
	
	private Integer idPesquisar;
	
	private List<Document> documents;
	
	@PostConstruct
	public void init() {
		documents = documentEJB.findAll();
	}
	
	public String inserir() {
		
		documentEJB.insert(document);
		 return null;
		
	}
	
	public String alterar() {
		
		documentEJB.update(document);
		return null;
	}
	
    public String apagar() {
		
		documentEJB.delete(document);
		return null;
	}

    public String novo() {
		
		document = new Document();
		return null;
	}
    
    
   public String buscaId() {
	   	
	   	document = documentEJB.load(idPesquisar);
		return null;
	}
    
    
    
    
	
	
	public Document getDocument() {
		
		if(document == null) {
			document = new Document();
		}
		return document;
	}

	public Integer getIdPesquisar() {
		return idPesquisar;
	}

	public void setIdPesquisar(Integer idPesquisar) {
		this.idPesquisar = idPesquisar;
	}

	public List<Document> getDocuments() {
		return documents;
	}

	public void setDocuments(List<Document> documents) {
		this.documents = documents;
	}

	
    
}
