package org.spring.orders.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.spring.orders.utils.Views;

import java.util.List;

@Entity
@Table(name = "users")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class StoreUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @JsonView(Views.UserSummary.class)
    private String name;

    @NotBlank
    @JsonView(Views.UserDetails.class)
    private String city;

    @Email
    @NotBlank
    @JsonView(Views.UserSummary.class)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonView(Views.UserDetails.class)
    private List<Order> orders;

    public StoreUser() {
    }

    public StoreUser(long id, String name, String city, String email, List<Order> orders) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.email = email;
        this.orders = orders;
    }

    public StoreUser(long id, String name, String city, String email) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.email = email;
    }

    public StoreUser(String name, String city, String email) {
        this.name = name;
        this.city = city;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public StoreUser setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public StoreUser setName(String name) {
        this.name = name;
        return this;
    }

    public String getCity() {
        return city;
    }

    public StoreUser setCity(String city) {
        this.city = city;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public StoreUser setEmail(String email) {
        this.email = email;
        return this;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public StoreUser setOrders(List<Order> orders) {
        this.orders = orders;
        return this;
    }
}
