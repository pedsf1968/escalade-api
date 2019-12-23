package com.dsf.escalade.model.global;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tag_list")
@IdClass(TagListId.class)
public class TagList implements Serializable {
   @Id
   private Integer topo;
   @Id
   private Integer tag;

}
