package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Embeddable
@Data
public class SpitPK implements Serializable {
   @Column(name = "topo_id", columnDefinition = "INTEGER(10)")
   private Integer topoId;
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "number", columnDefinition = "INTEGER(3)")
   private Integer number;

   public SpitPK() {super(); }
}
