package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Person person;

    @Column(length = 45)
    private String nr_document;

    @Column(length = 45)
    private String tp_document;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

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
}
