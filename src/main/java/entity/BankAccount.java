package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

private String accountNumber;
private double balance;

@ManyToOne
@JoinColumn(name = "customer_id", nullable = false)
private Customer customer;

public BankAccount() {
    }

    public BankAccount(String accountNumber, double balance, Customer customer) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.customer = customer;
    }
}
