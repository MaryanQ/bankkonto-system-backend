package edu.backkontosystembackend.repository;

import edu.backkontosystembackend.entity.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
