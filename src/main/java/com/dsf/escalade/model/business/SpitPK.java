package com.dsf.escalade.model.business;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class SpitPK implements Serializable {
   @Column(name = "topo_id")
   private Integer topoId;
   @Column(name = "number")
   private Integer number;

   public SpitPK() {super(); }
}
