package edu.backkontosystembackend.service;

import edu.backkontosystembackend.Mapper.TransactionMapper;
import edu.backkontosystembackend.dto.TransactionDTO;
import edu.backkontosystembackend.entity.BankAccount;
import edu.backkontosystembackend.entity.Transaction;
import edu.backkontosystembackend.repository.BankAccountRepository;
import edu.backkontosystembackend.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionService {

@Autowired
    private TransactionRepository transactionRepository;

@Autowired
    private BankAccountRepository bankAccountRepository;

public TransactionDTO createTransaction(TransactionDTO dto) {
    //find den tilknyttede konto
    BankAccount bankAccount = bankAccountRepository.findById(dto.getBankAccountId()).
            orElseThrow(() -> new RuntimeException("Kontoen findes ikke"));

    //bruge mapperen til at konvetere dto til entity
    Transaction transaction = TransactionMapper.toEntity(dto, bankAccount);

    //gem transaktionen
    transaction = transactionRepository.save(transaction);

    //brug mapperen til at konvetere entity til dto
    return TransactionMapper.toDto(transaction);
}

public List<TransactionDTO> getAllTransactions() {
    return transactionRepository.findAll().stream().map(TransactionMapper::toDto).collect(Collectors.toList());
}

public TransactionDTO getTransactionById(long id) {
    Transaction transaction = transactionRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Transaktionen findes ikke"));
    return TransactionMapper.toDto(transaction);

}

public void deleteTransaction(long id) {
    Transaction transaction = transactionRepository.findById(id).
            orElseThrow(() -> new RuntimeException("Transaktionen findes ikke"));
    transactionRepository.delete(transaction);
}







}
