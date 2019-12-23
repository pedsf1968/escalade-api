package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
public class SpitPK implements Serializable {
   @Column(name = "topo_id", columnDefinition = "INTEGER(10)")
   private Integer topo;
   @Column(name = "numero", columnDefinition = "INTEGER(3)")
   private Integer numero;

   public SpitPK() {super(); }
}
