package edu.backkontosystembackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;

    private LocalDate transactionDate;
    private double amount;
    private String transactionType;

  @ManyToOne
  @JoinColumn(name = "bank_account_id", nullable = false)
  private BankAccount bankAccount;

  public Transaction() {
    }

    public Transaction(LocalDate transactionDate, double amount, String transactionType) {
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
    }


}

