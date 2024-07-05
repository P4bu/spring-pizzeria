package com.pizza.spring_pizzeria.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity {

    @Id
    @Column(nullable = false, length = 20)
    private String username;

    @Column(nullable = false, length = 200)
    private String password;

    @Column(length = 50)
    private String email;

    @Column(nullable = false, columnDefinition = "smallint")
    //@Convert(converter = BooleanToSmallintConverter.class)
    private Boolean locked;

    @Column(nullable = false, columnDefinition = "smallint")
    //@Convert(converter = BooleanToSmallintConverter.class)
    private Boolean disable;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<UserRoleEntity> roles;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Boolean getDisable() {
        return disable;
    }

    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    public List<UserRoleEntity> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", locked=" + locked +
                ", disable=" + disable +
                ", roles=" + roles +
                '}';
    }
}
