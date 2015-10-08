package com.realdolmen.course.domain.auth;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

//@Entity
/*@NamedQueries({
        @NamedQuery(
                name = "User.find",
                query = "SELECT u FROM User u WHERE u.username = :username AND u.password = :password"),
        @NamedQuery(
                name = "User.list",
                query = "SELECT u FROM User u")
})
*/
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    private String password;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "UserRoles", joinColumns = { @JoinColumn(name = "userId") })
    @Column(name = "role")
    private List<Role> roles;

    public User(String username, String password/*,Role baseRole*/) {
        this.username = username;
        this.password = password;
        ///addRole(baseRole);
    }

    public User() {
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

    public int getId() {
        return id;
    }

    public void addRole(Role role)
    {
        if(roles==null)
        {
            roles = new ArrayList<>();
        }
        roles.add(role);
    }

    public void removeRole(Role role)
    {
        if(roles.contains(role))
        {
            roles.remove(role);
        }
    }

    private void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<Role> getRoles() {
        return roles;
    }
}

