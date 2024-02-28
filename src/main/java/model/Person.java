package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 200)
    private String nm_person;

    @Column(length = 20)
    private String tp_person;

    @Column(length = 200)
    private String nm_email;

    @Column(length = 200)
    private String nr_telephone;

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private List<Document> documents;

    // Getters and setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNm_person() {
        return nm_person;
    }

    public void setNm_person(String nm_person) {
        this.nm_person = nm_person;
    }

    public String getTp_person() {
        return tp_person;
    }

    public void setTp_person(String tp_person) {
        this.tp_person = tp_person;
    }

    public String getNm_email() {
        return nm_email;
    }

    public void setNm_email(String nm_email) {
        this.nm_email = nm_email;
    }

    public String getNr_telephone() {
        return nr_telephone;
    }

    public void setNr_telephone(String nr_telephone) {
        this.nr_telephone = nr_telephone;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }
}
