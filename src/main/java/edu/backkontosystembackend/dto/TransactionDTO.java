package edu.backkontosystembackend.dto;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class TransactionDTO {
    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private long id;
    private LocalDate transactionDate;
    private double amount;
    private String transactionType;
    private long bankAccountId;

    public TransactionDTO() {
    }

    public TransactionDTO(long id, LocalDate transactionDate, double amount, String transactionType, long bankAccountId) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.transactionType = transactionType;
        this.bankAccountId = bankAccountId;
    }
}

