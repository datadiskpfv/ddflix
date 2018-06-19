package uk.co.datadisk.ddflix.entities.user;

import lombok.*;
import uk.co.datadisk.ddflix.entities.AbstractDomainClass;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"city", "users"}, callSuper = false)
@ToString(exclude = {"city", "users"})
@Entity
@Builder
@Table(name = "addresses")
public class Address extends AbstractDomainClass {

  @NotNull
  @Column(name = "line1")
  private String line1;

  @Column(name = "line2")
  private String line2;

  @Column(name = "line3")
  private String line3;

  @Column(name = "line4")
  private String line4;

  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinColumn(name = "city_id")
  private City city;

  @NotNull
  @Column(name = "postcode")
  private String postcode;

  @ManyToMany(mappedBy = "Addresses")
  private Set<User> users = new HashSet<>();

  @Override
  public String toString() {
    return this.line1 + ", " +
            this.line2 + ", " +
            this.line3 + ", " +
            this.city.getCity() + ", " +
            this.city.getCountry().getCountry() + ", " +
            this.postcode;
  }
}
