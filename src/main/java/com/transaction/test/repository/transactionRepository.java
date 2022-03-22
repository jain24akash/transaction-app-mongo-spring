package com.transaction.test.repository;

import com.transaction.test.model.transactions;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface transactionRepository extends MongoRepository<transactions, String> {
}
