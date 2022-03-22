package com.transaction.test.controller;

import com.transaction.test.model.transactions;
import com.transaction.test.model.wallets;
import com.transaction.test.repository.transactionRepository;
import com.transaction.test.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class transactionController {
    @Autowired
    transactionRepository transRepo;
    @Autowired
    walletRepository walletRepo;
    @GetMapping("/transactions")
    public ResponseEntity<List<transactions>> getAllTransactions(){
        List<transactions> tr = new ArrayList<transactions>();
        transRepo.findAll().forEach(tr::add);
        return new ResponseEntity<>(tr, HttpStatus.OK);
    }

    @GetMapping("/transactions/{id}")
    public ResponseEntity<transactions> getOneTransaction(@PathVariable("id") String id){
        Optional<transactions> tr = transRepo.findById(id);
        if (tr.isPresent()){
            return new ResponseEntity<>(tr.get(),HttpStatus.OK);
        } else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/transactions/{walletId}")
    @Transactional
    public ResponseEntity<transactions> createTransaction(@PathVariable("walletId") String walletId, @RequestBody transactions transact){
        Optional<wallets> w = walletRepo.findById(walletId);
        if (w.isPresent()){
            try{
                wallets wall = w.get();
                wall.setBalance(transact.getAmount()+wall.getBalance());
                transact.setBalance(wall.getBalance());
                transactions tr = transRepo.save(transact);
                walletRepo.save(wall);
                return new ResponseEntity<>(tr,HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }
}
