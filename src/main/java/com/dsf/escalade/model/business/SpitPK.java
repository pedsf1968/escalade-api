package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SpitPK implements Serializable {
   @Column(name = "topo_id", columnDefinition = "INTEGER(10)")
   private Integer topoId;
   @Column(name = "number", columnDefinition = "INTEGER(3)")
   private Integer number;

   public SpitPK() {super(); }
}
