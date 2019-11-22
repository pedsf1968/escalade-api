package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Integer> {
   Utilisateur findByNom(String nom);
   Utilisateur findByMail(String email);
   Utilisateur findByLogin(String login);
}
