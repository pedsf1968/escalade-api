package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
   Utilisateur findByMail(String email);

   @Override
   Utilisateur save(Utilisateur utilisateur);

   @Override
   void delete(Utilisateur utilisateur);


   Utilisateur findByNom(String nom);
}
