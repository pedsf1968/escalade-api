package com.dsf.escalade.web.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class LongueurDto {
   private static final int NAME_MIN = 1;
   private static final int NAME_MAX = 50;
   private static final int HEIGHT_MAX = 50;

   private Integer id;
   @NotNull
   @Size(min = NAME_MIN, max = NAME_MAX)
   private String name;
   private Integer voieId;
   private Integer cotationId;

   @Size(max = HEIGHT_MAX)
   private Integer height;
   private Integer spits;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getVoieId() {
      return voieId;
   }

   public void setVoieId(Integer voieId) {
      this.voieId = voieId;
   }

   public Integer getCotationId() {
      return cotationId;
   }

   public void setCotationId(Integer cotationId) {
      this.cotationId = cotationId;
   }

   public Integer getHeight() {
      return height;
   }

   public void setHeight(Integer height) {
      this.height = height;
   }

   public Integer getSpits() {
      return spits;
   }

   public void setSpits(Integer spits) {
      this.spits = spits;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof LongueurDto)) return false;
      LongueurDto that = (LongueurDto) o;
      return getId().equals(that.getId()) &&
            getName().equals(that.getName()) &&
            getVoieId().equals(that.getVoieId()) &&
            Objects.equals(getCotationId(), that.getCotationId()) &&
            Objects.equals(getHeight(), that.getHeight()) &&
            Objects.equals(getSpits(), that.getSpits());
   }

   @Override
   public int hashCode() {
      return Objects.hash(getId(), getName(), getVoieId(), getCotationId(), getHeight(), getSpits());
   }

   @Override
   public String toString() {
      return "LongueurDto{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", voieId=" + voieId +
            ", cotationId=" + cotationId +
            ", height=" + height +
            ", spits=" + spits +
            '}';
   }
}
