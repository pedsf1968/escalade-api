package com.dsf.escalade.model.global;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tag")
public class Tag implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "name", columnDefinition = "VARCHAR(50) NOT NULL")
   private String name;
}
