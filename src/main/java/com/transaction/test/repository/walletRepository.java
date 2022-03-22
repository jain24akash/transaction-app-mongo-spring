package com.transaction.test.repository;

import com.transaction.test.model.wallets;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface walletRepository extends MongoRepository<wallets, String> {
}
