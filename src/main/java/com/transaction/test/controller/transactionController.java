package com.transaction.test.controller;

import com.transaction.test.model.transactions;
import com.transaction.test.repository.transactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class transactionController {
    @Autowired
    transactionRepository transRepo;
    @GetMapping("/transactions")
    public ResponseEntity<List<transactions>> getAllTransactions(){
        List<transactions> tr = new ArrayList<transactions>();
        transRepo.findAll().forEach(tr::add);
        return new ResponseEntity<>(tr, HttpStatus.OK);
    }
}
