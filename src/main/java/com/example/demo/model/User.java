package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.service.CustomGrantedAuthority;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    private String email;
    private String password;
    
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="users_roles",
            joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="id")},
            inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="id")})
    private List<Role> roles = new ArrayList<>();
    
    public User(String firstName, String lastName, String email, String password, List<Role> roles) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    
    public User() {
        // TODO Auto-generated constructor stub
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    public List<Role> getRoles() {
        return roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
    
    public List<GrantedAuthority> getAuthorities() {
        // Implement this method to return the list of user authorities
        // For example, you can return a list of roles or permissions
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<Role> roles = getRoles(); // Use the getRoles() method to retrieve the roles
        
        for (Role role : roles) {
            authorities.add(new CustomGrantedAuthority(role.getName()));
        }
        
        return authorities;
    }


    
    public String getUsername() {
        return email;
    }
    
    public boolean isAccountNonExpired() {
        // Implement this method to return whether the user account is non-expired
        return true;
    }
    
    public boolean isAccountNonLocked() {
        // Implement this method to return whether the user account is non-locked
        return true;
    }
    
    public boolean isCredentialsNonExpired() {
        // Implement this method to return whether the user credentials are non-expired
        return true;
    }
    
    public boolean isEnabled() {
        // Implement this method to return whether the user is enabled
        return true;
    }
}
