package com.transaction.test.controller;

import com.transaction.test.model.wallets;
import com.transaction.test.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class walletController {
    @Autowired
    walletRepository walletRepo;
    @GetMapping("/wallet")
    public ResponseEntity<List<wallets>> getAllWallets(){
        List<wallets> wallets = new ArrayList<wallets>();
        walletRepo.findAll().forEach(wallets::add);
        return new ResponseEntity<>(wallets, HttpStatus.OK);
    }

}
