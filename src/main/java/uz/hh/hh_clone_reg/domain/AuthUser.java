package uz.hh.hh_clone_reg.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class AuthUser extends AbsUUIDEntitiy  implements UserDetails {
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    @OneToOne(fetch = FetchType.LAZY)
    private Attachment avatar;
    private String initialLetter;
    private String color;
    private String emailCode;
    private boolean enabled;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    @Enumerated(EnumType.STRING)
    private AuthUserRole authUserRole;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.authUserRole.name());
        return Collections.singleton(simpleGrantedAuthority);
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    public AuthUser(String fullName, String email, String password, AuthUserRole authUserRole) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.authUserRole = authUserRole;
    }
}
