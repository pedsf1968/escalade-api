package com.dsf.escalade.repository.global;

import com.dsf.escalade.model.global.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireRepository extends JpaRepository <Commentaire, Long> {
}
