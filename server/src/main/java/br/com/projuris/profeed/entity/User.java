package br.com.projuris.profeed.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "pf_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User extends GenericEntity implements UserDetails {

    @NotNull(message = "Name cannot be null")
    private String nome;

    @NotNull(message = "Email cannot be null")
    @Email(message = "Email should be valid")
    private String email;

    @NotNull(message = "Active cannot be null")
    private Boolean active;

    private String photoUrl;

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
    }
    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User(@NotNull(message = "Name cannot be null") String nome,
                @NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String email,
                @NotNull(message = "Active cannot be null") Boolean active,
                @NotEmpty String username,
                @NotEmpty String password, List<String> roles) {
        this.nome = nome;
        this.email = email;
        this.active = active;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public User(@NotNull(message = "Name cannot be null") String nome,
                @NotNull(message = "Email cannot be null") @Email(message = "Email should be valid") String email,
                @NotNull(message = "Active cannot be null") Boolean active,
                @NotEmpty String username,
                @NotEmpty String password, List<String> roles,
                String photoUrl) {
        this.nome = nome;
        this.email = email;
        this.active = active;
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.photoUrl = photoUrl;
    }
}
