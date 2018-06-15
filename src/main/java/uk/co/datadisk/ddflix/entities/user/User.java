package uk.co.datadisk.ddflix.entities.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"roles", "passwordResetToken"}, callSuper = false)
@ToString(exclude = {"roles", "passwordResetToken"})
@Entity
public class User extends UserDetail {

    @Builder
    public User(Long id, boolean locked, boolean active, boolean expired, boolean enabled, String email, String username, String password) {
        super(id, locked, active, expired, enabled);
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @NotNull
    @Column(name = "email", unique=true)
    private String email;

    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "password")
    private String password;

    // Lazy loaded by default
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name="user_role",
            joinColumns = {@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="role_id")}
    )
    private Set<Role> roles = new HashSet<>();

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PasswordResetToken passwordResetToken;

    @OneToOne
    @JoinColumn(name = "defaultShippingAddress")
    private Address defaultShippingAddress;

    @OneToOne
    @JoinColumn(name = "defaultBillingAddress")
    private Address defaultBillingAddress;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name="user_shipping_address",
            joinColumns={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="address_id")}
    )
    private Set<Address> shippingAddresses = new HashSet<>();

    public void addRole(Role role) { this.roles.add(role);}
    public void removeRole(Role role) { this.roles.remove(role);}

    public void addShippingAddress(Address shippingAddress) { this.shippingAddresses.add(shippingAddress);}
    public void removeShippingAddress(Address shippingAddress) { this.shippingAddresses.remove(shippingAddress);}
}