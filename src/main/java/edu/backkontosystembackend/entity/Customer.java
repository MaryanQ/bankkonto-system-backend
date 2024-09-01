package edu.backkontosystembackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

private String name;
private String email;
private double balance;

@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
private Set<BankAccount> bankAccount;

public Customer() {
    }

    public Customer(String name, String email, double balance) {
        this.name = name;
        this.email = email;
        this.balance = balance;
    }

    public Customer(long id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
    }
}
