package com.dsf.escalade.model.global;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private Integer id;
   @Column(name = "site_id", nullable = false)
   private Integer siteId;
   @Column(name = "user_id", nullable = false)
   private Integer userId;
   @Column(name = "text", nullable = false, columnDefinition = "TEXT")
   private String text;
}
