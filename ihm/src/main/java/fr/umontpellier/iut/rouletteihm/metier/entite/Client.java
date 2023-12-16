package fr.umontpellier.iut.rouletteihm.metier.entite;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.id.IncrementGenerator;

@Entity
@Table(name = "ClientsIHM")
public class Client {

    @Id
    @GenericGenerator(name = "idCAuto", type = IncrementGenerator.class)
    @GeneratedValue(generator = "idCAuto")
    private int idClient;

    @Column
    private String nom;
    @Column
    private String prenom;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private String paswwordConfirm;
    @Column
    private int solde;

    public Client(String nom, String prenom, String email, String pwd, String pswConfirmn, int s) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        password = pwd;
        paswwordConfirm = pswConfirmn;
        solde = s;

    }

    public Client() {

    }


    public int getIdClient() {
        return idClient;
    }

    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPaswwordConfirm() {
        return paswwordConfirm;
    }

    public void setPaswwordConfirm(String paswwordConfirm) {
        this.paswwordConfirm = paswwordConfirm;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s, %s: %s",
                this.prenom != null ? this.prenom : "",
                this.nom != null ? this.nom : "",
                this.email != null ? this.email : "",
                this.password != null ? this.password : "",
                this.paswwordConfirm != null ? this.paswwordConfirm : "",
                this.solde != 0 ? this.solde : "N/A");
    }


}
