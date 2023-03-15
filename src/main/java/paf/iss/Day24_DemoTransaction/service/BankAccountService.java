package paf.iss.Day24_DemoTransaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import paf.iss.Day24_DemoTransaction.model.BankAccount;
import paf.iss.Day24_DemoTransaction.repository.BankAccountRepository;

@Service
public class BankAccountService {
    
    @Autowired
    private BankAccountRepository bankAcctRepo;

    @Transactional(isolation = Isolation.READ_UNCOMMITTED)
    public Boolean transferMoney(Integer accountFrom, Integer accountTo, Float amount) {

        Boolean bTransferred = false;
        Boolean bWithdrawn = false;
        Boolean bDeposited = false;
        Boolean bCanWithdraw = false;

        BankAccount depositAccount = null;
        BankAccount withdrawlAccount = null;
        Boolean bDepositAccountValid = false;
        Boolean bWithdrawalAccountValid = false;
        Boolean bProceed = false;

        // Check if accounts are active
        depositAccount = bankAcctRepo.retrieveAccountDetails(accountTo);
        withdrawlAccount = bankAcctRepo.retrieveAccountDetails(accountFrom);
        bDepositAccountValid = depositAccount.getIsActive();
        bWithdrawalAccountValid = withdrawlAccount.getIsActive();

        if (bDepositAccountValid && bWithdrawalAccountValid) {
            bProceed = true;
        }

        // Check if withdrawn account have enough money to withdraw
        if (bProceed) {
            if (withdrawlAccount.getBalance() >= amount) {
                bCanWithdraw = true;
            }
            else {
                bProceed = false;
            }
        }

        if (bProceed) {
            // Perform withdrawal (requires transaction)
            bWithdrawn = bankAcctRepo.withdrawAmount(accountFrom, amount);

            // Perform deposit (requires transaction)
            bDeposited = bankAcctRepo.depositAmount(accountTo, amount);
        }
            
        // return transaction successful
        if (bWithdrawn && bDeposited) {
            bTransferred = true;
        }

        return bTransferred;
    }

}
