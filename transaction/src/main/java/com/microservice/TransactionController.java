package com.microservice;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.service.TransactionService;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;

    @PostMapping("/register")
    public ResponseEntity<HashMap<String, Object>> registerTransaction(Transaction transaction) {
        HashMap<String, Object> response = new HashMap<>();
        if(transaction != null) {
            transactionService.registerTransaction(transaction);
            response.put("message", "Transaction registrada");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Transaction no registrada");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
            
    }

    @PostMapping("/pay")
    public ResponseEntity<HashMap<String, Object>> payTransaction(long idTransaction, float amount) {
        HashMap<String, Object> response = new HashMap<>();
        if(amount > 0) {
            //Obtener el id TypeService para obtener el pago del servicio
            transactionService.payTransaction(idTransaction, amount);
            response.put("message", "Transaccion exitosa!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "Transaccion invalida");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getTransactionId")
    public ResponseEntity<HashMap<String, Object>> getTransactionId(long idCustomer, long idService) {
        if(idCustomer > 0 && idService > 0) {
            HashMap<String, Object> response = new HashMap<>();
            response.put("idTransaction", transactionService.getTransactionId(idCustomer, idService));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/getAllTransactions")
    public ResponseEntity<HashMap<String, Object>> getAllTransactions() {
        HashMap<String, Object> response = new HashMap<>();
        if(transactionService.getAllTransactions().size() > 0) {
            response.put("transactions", transactionService.getAllTransactions());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.put("message", "No hay transferencia");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    

}
