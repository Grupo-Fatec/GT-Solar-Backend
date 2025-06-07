package org.github.gabrielgodoi.gtsolarbackend.entities.admins;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.github.gabrielgodoi.gtsolarbackend.dto.commission.Commission;
import org.github.gabrielgodoi.gtsolarbackend.enums.AdminRole;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin")
public class Admin implements UserDetails {
    @Id
    private String id;
    private String name;
    @Indexed(unique = true)
    private String email;
    private String password;
    private AdminRole adminRole;
    private List<Commission> comissionsList = new ArrayList<>();
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.adminRole == AdminRole.SUPER_ADMIN)
            return List.of(
                    new SimpleGrantedAuthority("MANAGER"),
                    new SimpleGrantedAuthority("SELLER"),
                    new SimpleGrantedAuthority("SUPPORT"),
                    new SimpleGrantedAuthority("ADMIN")
                    );
        else return List.of(new SimpleGrantedAuthority("SUPPORT"));
    }

    @Override
    public String getUsername() {
        return "";
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
