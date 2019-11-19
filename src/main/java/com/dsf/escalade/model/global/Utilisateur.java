package com.dsf.escalade.model.global;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;

@Entity
public class Utilisateur {
   @Id
   @GeneratedValue(strategy =  GenerationType.AUTO)
   @Column(name = "id")
   private Long id;
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
   @Column(name = "est_membre")
   private Boolean estMembre;
   @Column(name = "adresse_id")
   private Long adresse;
   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
   private Collection<Role> roles;

   public Utilisateur() {
   }

   public Utilisateur(Long id, Civilite civilite, String nom, String prenom, String telephone, String mail, String login, String motDePasse) {
      this.id = id;
      this.civilite = civilite;
      this.nom = nom;
      this.prenom = prenom;
      this.telephone = telephone;
      this.mail = mail;
      this.login = login;
      this.motDePasse = motDePasse;
   }

   public Utilisateur(Long id, Civilite civilite, String nom, String prenom, String telephone, String mail, String login, String motDePasse, String confirmationMotDePasse, Set<Role> roles, Boolean estMembre, Long adresse) {
      this.id = id;
      this.civilite = civilite;
      this.nom = nom;
      this.prenom = prenom;
      this.telephone = telephone;
      this.mail = mail;
      this.login = login;
      this.motDePasse = motDePasse;
      this.estMembre = estMembre;
      this.adresse = adresse;
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
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
      return "Utilisateur{" +
            "id=" + id +
            ", civilite=" + civilite +
            ", nom='" + nom + '\'' +
            ", prenom='" + prenom + '\'' +
            ", telephone='" + telephone + '\'' +
            ", mail='" + mail + '\'' +
            ", login='" + login + '\'' +
            ", motDePasse='" + motDePasse + '\'' +
            ", roles=" + roles +
            ", estMembre=" + estMembre +
            ", adresse=" + adresse +
            '}';
   }
}
