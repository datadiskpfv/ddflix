package uk.co.datadisk.ddflix.entities;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"cities"})
@Entity
@Table(name = "country")
public class Country {

  @Id
  @Column(name = "id")
  private String country;

  @NotNull
  @Column(name = "iso", unique = true)
  private String iso;

  @NotNull
  @Column(name = "tld", unique = true)
  private String tld;

  @NotNull
  @Column(name = "name", unique = true)
  private String name;

  @CreationTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "date_created", updatable = false)
  private Date dateCreated;

  @UpdateTimestamp
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "last_updated")
  private Date lastUpdated;

  @OneToMany(mappedBy="city")
  private Set<City> cities = new HashSet<>();

  @Override
  public String toString(){
    return country;
  }
}