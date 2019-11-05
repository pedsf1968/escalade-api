package com.dsf.escalade.dao.global;

import com.dsf.escalade.model.global.Commentaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentaireDao extends JpaRepository <Commentaire, Integer> {
}
