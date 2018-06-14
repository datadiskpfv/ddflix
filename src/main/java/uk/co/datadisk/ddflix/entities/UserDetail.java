package uk.co.datadisk.ddflix.entities;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class UserDetail extends AbstractDomainClass implements UserDetails {

    @Column(name ="locked")
    @ColumnDefault("false")
    private boolean locked;

    @Column(name = "active")
    @ColumnDefault("true")
    private boolean active = true;

    @Column(name = "expired")
    @ColumnDefault("false")
    private boolean expired;

    @Column(name = "enabled")
    @ColumnDefault("true")
    private boolean enabled = true;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    // this will be used by the subclass builder
    public UserDetail(Long id, boolean locked, boolean active, boolean expired, boolean enabled) {
        super(id);
        this.locked = locked;
        this.active = active;
        this.expired = expired;
        this.enabled = enabled;
    }
}
