package uk.co.datadisk.ddflix.entities;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "users", callSuper = false)
@ToString(exclude = "users")
@Entity
public class Role extends AbstractDomainClass {

    @Column(name = "name", unique = true)
    private String name;

    // Lazy loaded by default
    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();

    public void addUser(User user){
        this.users.add(user);
    }
    public void removeUser(User user){ this.users.remove(user); }

    // having to do below as I am using inheritance
    @Builder
    public Role(Long id, String name) {
        super(id);
        this.name = name;
    }

    @Override public String toString() {
        return this.getName();
    }
}