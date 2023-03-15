package paf.iss.Day24_DemoTransaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import paf.iss.Day24_DemoTransaction.payload.TransferRequest;
import paf.iss.Day24_DemoTransaction.service.BankAccountService;

@RestController
@RequestMapping("/accounts")
public class BankAccountController {
    
    @Autowired
    private BankAccountService bankAcctSvc;

    @PostMapping
    public ResponseEntity<Boolean> transferMoney(@RequestBody TransferRequest transferRequest) {
        Boolean bTransferred = false;

        bTransferred = bankAcctSvc.transferMoney(transferRequest.getAccountFrom(), transferRequest.getAccountTo(), transferRequest.getAmount());

        if (bTransferred) {
            return ResponseEntity.status(HttpStatus.OK).body(bTransferred);
        }
        else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(bTransferred);
        }
    }

}
