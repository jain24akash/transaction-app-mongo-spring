package com.transaction.test.controller;

import com.transaction.test.model.wallets;
import com.transaction.test.repository.walletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/wallet/{walletId}")
    public ResponseEntity<wallets> getOneWallet(@PathVariable("walletId") String walletId){
        Optional<wallets> w = walletRepo.findById(walletId);
        if (w.isPresent()){
            return new ResponseEntity<>(w.get(),HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/setup")
    public ResponseEntity<wallets> createWallet(@RequestBody wallets wallet){
        try{
            wallets w = walletRepo.save(wallet);
            return new ResponseEntity<>(w,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/wallet/{walletId}")
    public ResponseEntity<wallets> updateWallet(@PathVariable("walletId") String walletId, @RequestBody wallets wallet){
        Optional<wallets> w = walletRepo.findById(walletId);
        if(w.isPresent()){
            wallets _w = w.get();
            _w.setName(wallet.getName());
            _w.setBalance(wallet.getBalance());
            return new ResponseEntity<>(walletRepo.save(_w), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/wallet/{walletId}")
    public ResponseEntity<HttpStatus> deleteWallet(@PathVariable("walletId") String walletId){
        try{
            walletRepo.deleteById(walletId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
