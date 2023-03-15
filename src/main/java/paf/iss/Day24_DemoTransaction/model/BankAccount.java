package paf.iss.Day24_DemoTransaction.model;

public class BankAccount {
    
    private Integer id;

    private String fullName;

    private Boolean isActive;

    private String accountType;

    private Float balance;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public BankAccount() {
    }

    public BankAccount(Integer id, String fullName, Boolean isActive, String accountType, Float balance) {
        this.id = id;
        this.fullName = fullName;
        this.isActive = isActive;
        this.accountType = accountType;
        this.balance = balance;
    }
    
}
