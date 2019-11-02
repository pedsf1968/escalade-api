package com.dsf.escalade.model.global;

import javax.persistence.*;

@Entity
public class Utilisateur {
   @Id
   //@GeneratedValue(strategy =  GenerationType.AUTO)
   private Integer id;
   @Column(name = "civilite")
   @Enumerated(EnumType.STRING)
   private Civilite civilite;
   @Column(name = "nom")
   private String nom;
   @Column(name = "prenom")
   private String prenom;
   @Column(name = "pseudo")
   private String pseudo;
   @Column(name = "telephone")
   private String telephone;
   @Column(name = "mail")
   private String mail;
   @Column(name = "login")
   private String login;
   @Column(name = "mot_de_passe")
   private String motDePasse;
   @Column(name = "est_membre")
   private Boolean estMembre;
   @Column(name = "adresse_id")
   private Integer adresse;

   public Utilisateur() {
   }

   public Utilisateur(Integer id, Civilite civilite, String nom, String prenom, String mail, String login, String motDePasse) {
      this.id = id;
      this.civilite = civilite;
      this.nom = nom;
      this.prenom = prenom;
      this.pseudo = null;
      this.telephone = null;
      this.mail = mail;
      this.login = login;
      this.motDePasse = motDePasse;
      this.estMembre = false;
      this.adresse = null;
   }


   public Utilisateur(Integer id, Civilite civilite, String nom, String prenom, String pseudo, String telephone, String mail, String login, String motDePasse, Boolean isMenbre, Integer adresse) {
      this.id = id;
      this.civilite = civilite;
      this.nom = nom;
      this.prenom = prenom;
      this.pseudo = pseudo;
      this.telephone = telephone;
      this.mail = mail;
      this.login = login;
      this.motDePasse = motDePasse;
      this.estMembre = estMembre;
      this.adresse = adresse;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public Civilite getCivilite() {
      return civilite;
   }

   public void setCivilite(Civilite civilite) {
      this.civilite = civilite;
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

   public String getPseudo() {
      return pseudo;
   }

   public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
   }

   public String getTelephone() {
      return telephone;
   }

   public void setTelephone(String telephone) {
      this.telephone = telephone;
   }

   public String getMail() {
      return mail;
   }

   public void setMail(String mail) {
      this.mail = mail;
   }

   public String getLogin() {
      return login;
   }

   public void setLogin(String login) {
      this.login = login;
   }

   public String getMotDePasse() {
      return motDePasse;
   }

   public void setMotDePasse(String motDePasse) {
      this.motDePasse = motDePasse;
   }

   public Boolean getEstMembre() {
      return estMembre;
   }

   public void setEstMembre(Boolean membre) {
      estMembre = membre;
   }

   public Integer getAdresse() {
      return adresse;
   }

   public void setAdresse(Integer adresse) {
      this.adresse = adresse;
   }

   @Override
   public String toString() {
      return "Utilisateur{" +
            "id=" + id +
            ", civilite='" + civilite + '\'' +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", pseudo='" + pseudo + '\'' +
            ", telephone='" + telephone + '\'' +
            ", mail='" + mail + '\'' +
            ", login='" + login + '\'' +
            ", motDePasse='" + motDePasse + '\'' +
            ", isMenbre=" + estMembre +
            ", adresse=" + adresse +
            '}';
   }
}
