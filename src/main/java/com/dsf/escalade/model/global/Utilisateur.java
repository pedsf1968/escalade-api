package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "utilisateur")
public class Utilisateur {

   @Id
   @Column(name = "id")
   @GeneratedValue(strategy =  GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "civilite", columnDefinition = "VARCHAR(4) DEFAULT 'M'")
   @Enumerated(EnumType.STRING)
   private Civilite civilite;

   @Column(name = "nom", columnDefinition = "VARCHAR(50) NOT NULL")
   private String nom;

   @Column(name = "prenom", columnDefinition = "VARCHAR(50) NOT NULL")
   private String prenom;

   @Column(name = "telephone", columnDefinition = "VARCHAR(10)")
   private String telephone;

   @Column(name = "mail", columnDefinition = "VARCHAR(255) NOT NULL")
   private String mail;

   @Column(name = "login", columnDefinition = "VARCHAR(20) NOT NULL")
   private String login;

   @Column(name = "mot_de_passe", columnDefinition = "VARCHAR(255) NOT NULL")
   private String motDePasse;
   private boolean enabled;
   private boolean tokenExpired;

   @Column(name = "est_membre")
   private Boolean estMembre;
   @Column(name = "adresse_id")
   private Long adresse;

   @ManyToMany//(fetch = FetchType.EAGER)
   @JoinTable(
         name = "users_roles",
         joinColumns = @JoinColumn(
               name = "user_id", referencedColumnName = "id"),
         inverseJoinColumns = @JoinColumn(
               name = "role_id", referencedColumnName = "id"))
   private Collection<Role> roles;

   public Utilisateur() {
      super();
   }

   public Utilisateur(Civilite civilite, String nom, String prenom, String mail, String login, String motDePasse) {
      this.civilite = civilite;
      this.nom = nom;
      this.prenom = prenom;
      this.mail = mail;
      this.login = login;
      this.motDePasse = motDePasse;
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

   public boolean isEnabled() {
      return enabled;
   }

   public void setEnabled(boolean enabled) {
      this.enabled = enabled;
   }

   public boolean isTokenExpired() {
      return tokenExpired;
   }

   public void setTokenExpired(boolean tokenExpired) {
      this.tokenExpired = tokenExpired;
   }

   public Boolean getEstMembre() {
      return estMembre;
   }

   public void setEstMembre(Boolean membre) {
      estMembre = membre;
   }

   public Long getAdresse() {
      return adresse;
   }

   public void setAdresse(Long adresse) {
      this.adresse = adresse;
   }

   public Collection<Role> getRoles() {
      return roles;
   }

   public void setRoles(Collection<Role> roles) {
      this.roles = roles;
   }

   @Override
   public String toString() {
      final StringBuilder builder = new StringBuilder();
      builder.append("Utilisateur [")
            .append("id=").append(id)
            .append(", civilite=").append(civilite)
            .append(", nom=").append(nom)
            .append(", prenom=").append(prenom)
            .append(", telephone=").append(telephone)
            .append(", mail=").append(mail)
            .append(", login=").append(login)
            .append(", motDePasse=").append(motDePasse)
            .append(", roles=").append(roles)
            .append("]");

      return builder.toString();
   }
}
