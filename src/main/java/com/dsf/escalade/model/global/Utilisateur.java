package com.dsf.escalade.model.global;

public class Utilisateur {
   private Integer id;
   private String civilite;
   private String nom;
   private String prenom;
   private String pseudo;
   private String telephone;
   private String mail;
   private String login;
   private String motDePasse;
   private Boolean isMenbre;
   private Integer adresse;

   public Utilisateur() {
   }

   public Utilisateur(Integer id, String civilite, String nom, String prenom, String pseudo, String telephone, String mail, String login, String motDePasse, Boolean isMenbre, Integer adresse) {
      this.id = id;
      this.civilite = civilite;
      this.nom = nom;
      this.prenom = prenom;
      this.pseudo = pseudo;
      this.telephone = telephone;
      this.mail = mail;
      this.login = login;
      this.motDePasse = motDePasse;
      this.isMenbre = isMenbre;
      this.adresse = adresse;
   }

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCivilite() {
      return civilite;
   }

   public void setCivilite(String civilite) {
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

   public Boolean getMenbre() {
      return isMenbre;
   }

   public void setMenbre(Boolean menbre) {
      isMenbre = menbre;
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
            ", isMenbre=" + isMenbre +
            ", adresse=" + adresse +
            '}';
   }
}
