package com.github.tobiasmiosczka.discordstats.model.platform;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.github.tobiasmiosczka.discordstats.model.BaseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.UniqueConstraint;
import java.util.Collection;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class User extends BaseEntity implements UserDetails {
    @Column(nullable = false, length = 60, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false, length = 60)
    private String password;

    @Column(nullable = false, length = 254, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(length = 128)
    private String firstname;

    @Column(length = 128)
    private String lastname;

    private boolean enabled;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}))
    @Enumerated(EnumType.STRING)
    private Set<Role> role;

    private Date birthdate;

    public User() {
        super();
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public Set<Role> getRole() {
        return role;
}

    public void setRole(Set<Role> role) {
        this.role = role;
    }

    public void addRole(Role role) {
        this.role.add(role);
    }

    public void removeRole(Role role) {
        this.role.remove(role);
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /**Implementation of UserDetails*/



    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
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
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.stream().map((role -> new SimpleGrantedAuthority(role.toString().toLowerCase()))).collect(Collectors.toSet());
    }
}
