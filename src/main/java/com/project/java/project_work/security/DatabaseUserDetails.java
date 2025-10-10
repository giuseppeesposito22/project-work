package com.project.java.project_work.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.project.java.project_work.model.Role;
import com.project.java.project_work.model.User;

public class DatabaseUserDetails implements UserDetails{

    private Integer id;
    private String username;
    private String password;
    private Set<GrantedAuthority> authorities;





    public DatabaseUserDetails(User user){
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.authorities = new HashSet<>();

        for (Role role : user.getRoles()) {

            authorities.add(new SimpleGrantedAuthority(role.getName()));
            
        }
    }

    @Override
    public boolean isAccountNonExpired(){
        return true;
    }

    @Override
    public boolean isAccountNonLocked(){
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }

    @Override
    public boolean isEnabled(){
        return true;
    }







    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }



    

}
