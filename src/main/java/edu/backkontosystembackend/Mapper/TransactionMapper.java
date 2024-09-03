package edu.backkontosystembackend.Mapper;

import edu.backkontosystembackend.dto.TransactionDTO;
import edu.backkontosystembackend.entity.Transaction;
import edu.backkontosystembackend.entity.BankAccount;

public class TransactionMapper {

    // Konverterer fra entity til DTO
    public static TransactionDTO toDto(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        TransactionDTO dto = new TransactionDTO();
        dto.setId(transaction.getId());
        dto.setTransactionDate(transaction.getTransactionDate());
        dto.setAmount(transaction.getAmount());
        dto.setTransactionType(transaction.getTransactionType());
        dto.setBankAccountId(transaction.getBankAccount().getId());

        return dto;
    }

    // Konverterer fra DTO til entity
    public static Transaction toEntity(TransactionDTO dto, BankAccount bankAccount) {
        if (dto == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setTransactionDate(dto.getTransactionDate());
        transaction.setAmount(dto.getAmount());
        transaction.setTransactionType(dto.getTransactionType());
        transaction.setBankAccount(bankAccount);

        return transaction;
    }
}
