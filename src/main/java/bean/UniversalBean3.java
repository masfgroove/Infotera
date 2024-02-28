package bean;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.PrimeFaces;

import dao.DocumentEJB;
import dao.UniversalDAO;
import model.Document;
import model.Person;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Named
@RequestScoped
public class UniversalBean3 {

	//***********************************************
	//Documents

	@EJB
	private UniversalDAO documentEJB;
	@EJB
	private UniversalDAO personEJB;
	private Document document1;
	
	private Integer idPesquisar;

	
	
	private List<Document> document1s;
	
	public String inserir() {
		
		documentEJB.insert1(document1);
		 return null;
		
	}
	public String alterar1() {
	    // Carregar o documento existente do banco de dados
	    Document existingDocument = documentEJB.load1(document1.getId());
	    
	    // Verificar se o documento existe
	    if (existingDocument != null) {
	        // Atualizar apenas os campos que podem ser alterados
	        existingDocument.setNr_document(document1.getNr_document());
	        existingDocument.setTp_document(document1.getTp_document());
	        
	        // Salvar as alterações
	        documentEJB.update1(existingDocument);

	        // Exibir mensagem de sucesso
	     }
	    
	 // Fechar o diálogo
	    PrimeFaces.current().executeScript("PF('dlg').hide();");

	    
	    return null;
	}

	
    public String apagar1() {
		
		documentEJB.delete1(document1);
		return null;
	}

    public String novo() {
		
		document1 = new Document();
		return null;
	}
    
    
   public String buscaId() {
	   	
	   	document1 = documentEJB.load1(idPesquisar);
		return null;
	}
    
   
    
    
	
	
	public Document getDocument1() {
		
		if(document1 == null) {
			document1 = new Document();
		}
		return document1;
	}

	public Integer getIdPesquisar() {
		return idPesquisar;
	}

	public void setIdPesquisar(Integer idPesquisar) {
		this.idPesquisar = idPesquisar;
	}
	
	
	

	public UniversalDAO getDocumentEJB() {
		return documentEJB;
	}
	public void setDocumentEJB(UniversalDAO documentEJB) {
		this.documentEJB = documentEJB;
	}
	
	public UniversalDAO getDao() {
		return dao;
	}
	public void setDao(UniversalDAO dao) {
		this.dao = dao;
	}
	public void setDocument1(Document document1) {
		this.document1 = document1;
	}
	public List<Document> getDocument1s() {
		return document1s;
	}

	public void setDocument1s(List<Document> document1s) {
		this.document1s = document1s;
	}


	private Document selectedDocument1;

	public Document getSelectedDocument1() {
	    return selectedDocument1;
	}

	public void setSelectedDocument1(Document selectedDocument1) {
	    this.selectedDocument1 = selectedDocument1;
	}
	
	
	
	//**********************************************
	
	
	
    @EJB
    private UniversalDAO dao;
    
    private Integer documentId;

    private Document selectedDocument;

    private List<Document> personDocuments;

    private Integer personId;
    private Person person;
    private Document document;
    private List<Person> persons;
    private List<Document> documents;
    private Document editDocumentId;
	
    @PostConstruct
    public void init() {
        person = new Person();
        document = new Document();
        selectedDocument = new Document();
        documents = dao.findAllDocuments();
		document1s = documentEJB.findAll();

        
        loadPersonData();
        loadDocumentData(); // Chamar o método loadDocumentData() ao inicializar o bean
        //clearDocumentFields(); // Limpar os campos do documento ao inicializar

        // Obter o valor de person_id dos parâmetros da requisição
        Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        if (params.containsKey("person_id")) {
            editDocumentPersonId = Integer.parseInt(params.get("person_id"));
        }
    }
    
    
    
    
    
    
	public UniversalDAO getPersonEJB() {
		return personEJB;
	}
	public void setPersonEJB(UniversalDAO personEJB) {
		this.personEJB = personEJB;
	}
	public Integer getDocumentId() {
		return documentId;
	}




	public void setDocumentId(Integer documentId) {
		this.documentId = documentId;
	}




	public void selectDocument(Document doc) {
        if (doc != null) {
            selectedDocument = doc;
            if (selectedDocument.getPerson() == null) {
                selectedDocument.setPerson(new Person());
            }
            PrimeFaces.current().executeScript("PF('editDialog').show();");
        } else {
            System.out.println("Selected document is null.");
        }
    }

    
    private Integer searchId;

    public Integer getSearchId() {
        return searchId;
    }

    public void setSearchId(Integer searchId) {
        this.searchId = searchId;
    }

    public void searchDocumentsById() {
        if (searchId != null) {
            documents = dao.findDocumentsByPersonId(searchId);
        } else {
            System.out.println("ID de busca é nulo.");
        }
    }

    private Integer editDocumentPersonId;

    public Integer getEditDocumentPersonId() {
        return editDocumentPersonId;
    }
    
    

    public void setEditDocumentPersonId(Integer editDocumentPersonId) {
        this.editDocumentPersonId = editDocumentPersonId;
    }

    public Document getSelectedDocument() {
        return selectedDocument;
    }

    public void setSelectedDocument(Document selectedDocument) {
        this.selectedDocument = selectedDocument;
    }

    public String encaminha() throws IOException {

        // Redirecionar para a página de sucesso
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.redirect(ec.getRequestContextPath() + "/editDocument.xhtml");
return null;
         
    }



   
    public String alterar() {
        if (selectedDocument != null) {
            dao.updateDocument(selectedDocument);
            // Recarregue a lista de documentos após a alteração
            personDocuments = dao.findDocumentsByPersonId(personId);
            return "success"; // ou outra página de destino
        } else {
            System.out.println("Selected document is null.");
            return null; // ou outra página de destino em caso de erro
        }
    }


    public String savePerson() {
        dao.insertPerson(person);
        persons = dao.findAllPersons();
        person = new Person();

        // Obter o ID do último cliente salvo
        Integer personId = dao.findLastPersonId(); // Implemente esse método na sua classe dao

        // Redirecionar para a página de cadastro de documentos com o ID do cliente como parâmetro
        return "cadastroDocumento.xhtml?faces-redirect=true&id=" + personId;
    }

    public List<Document> getPersonDocuments() {
        return personDocuments;
    }

    public void setPersonDocuments(List<Document> personDocuments) {
        this.personDocuments = personDocuments;
    }

    private String nr_document;
    private String tp_document;

    public String getNr_document() {
        return nr_document;
    }

    public void setNr_document(String nr_document) {
        this.nr_document = nr_document;
    }

    public String getTp_document() {
        return tp_document;
    }

    public void setTp_document(String tp_document) {
        this.tp_document = tp_document;
    }

    public Document getEditDocumentId() {
        return editDocumentId;
    }

    public void setEditDocumentId(Document editDocumentId) {
        this.editDocumentId = editDocumentId;
    }

    public void editDocument(Document doc) {
        selectedDocument = doc;
        // Preencha os campos do formulário com os dados do documento selecionado
        nr_document = selectedDocument.getNr_document();
        tp_document = selectedDocument.getTp_document();
        PrimeFaces.current().executeScript("PF('editDialog').show();");
    }

    public void clearDocumentFields() {
        nr_document = null;
        tp_document = null;
    }

    public void updateDocument() {
        try {
            UniversalDAO dao = new UniversalDAO(); // Instância do seu DAO
            dao.updateDocument(selectedDocument); // Atualiza o documento no banco de dados
            // Atualiza a lista de documentos após a alteração
            documents = dao.findAllDocuments();
        } catch (Exception e) {
            // Trate a exceção adequadamente
            e.printStackTrace();
        }
    }

    public void updatePerson() {
        try {
            UniversalDAO dao = new UniversalDAO(); // Instância do seu DAO
            dao.updateDocument(selectedDocument);
            dao.updatePerson(person);
            // Atualiza o documento no banco de dados
            // Atualiza a lista de documentos após a alteração
            documents = dao.findAllDocuments();
        } catch (Exception e) {
            // Trate a exceção adequadamente
            e.printStackTrace();
        }
    }
    
    
    private String nrDocument;
    private String tpDocument;

    public String getNrDocument() {
        return nrDocument;
    }

    public void setNrDocument(String nrDocument) {
        this.nrDocument = nrDocument;
    }

    public String getTpDocument() {
        return tpDocument;
    }

    public void setTpDocument(String tpDocument) {
        this.tpDocument = tpDocument;
    }

    public void loadDocumentData() {
        if (editDocumentId != null) {
            selectedDocument = dao.findDocumentById(editDocumentId);
        } else {
            selectedDocument = new Document();
        }

        if (selectedDocument != null && selectedDocument.getPerson() != null) {
            editDocumentPersonId = selectedDocument.getPerson().getId();
        } else {
            editDocumentPersonId = null;
        }
        if (selectedDocument != null) {
            nrDocument = selectedDocument.getNr_document();
            tpDocument = selectedDocument.getTp_document();
        }
    }

    public void loadPersonData() {
        if (personId != null) {
            person = dao.findPersonById(personId);
            if (person == null) {
                System.out.println("Person not found for id: " + personId);
            } else {
                System.out.println("Loaded person: " + person.getNm_person());
                // Carrega os documentos relacionados ao ID da pessoa
                personDocuments = dao.findDocumentsByPersonId(personId);
            }
        } else {
            System.out.println("No person id provided.");
        }

    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String saveDocument() {
        loadPersonData(); // Carregar os dados do cliente
        document.setPerson(person); // Associar o documento à pessoa correta
        dao.insertDocument(document);
        documents = dao.findAllDocuments();
        document = new Document();
        
        PrimeFaces.current().executeScript("PF('dlg').show();");
        document = new Document();
        // Adicionar mensagem de sucesso
     
        return null; // ou a página de redirecionamento desejada
    }


    public void newDocument() {
        document = new Document();
    }

    // Getters and setters

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Person> getPersons() {
        if (persons == null) {
            persons = dao.findAllPersons();
        }
        return persons;
    }

    public void setPersons(List<Person> persons) {
        this.persons = persons;
    }

    public List<Document> getDocuments() {
        if (documents == null) {
            documents = dao.findAllDocuments();
        }
        return documents;
    }
    
    public void prepareEditDocument(Document doc) {
        selectedDocument = doc;
    }


    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
    
    public void findDocumentsByPersonId() {
        if (personId != null) {
            personDocuments = dao.findDocumentsByPersonId(personId);
        } else {
            personDocuments = new ArrayList<>();
        }
    }
    
    
    


}
