package Bank;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Random;
import java.time.format.DateTimeFormatter;

public class BankAccount {
    //поля класса
    private String ownerName;
    private int balance;
    private LocalDateTime openingDate;
    private boolean isBlocked;
    private String accountNumber;

    //конструктор
    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0; //начальный баланс
        this.openingDate = LocalDateTime.now(); //текущая дата
        this.isBlocked = false; //счет не заблокирован по умолчанию
        this.accountNumber = generateAccountNumber(); //генерация номера счета
    }

    //генерация номера счета
    private String generateAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    //пополнение счета
    public boolean deposit(int amount) {
        if (isBlocked || amount <= 0) {
            return false;
        }
        balance += amount;
        return true;
    }

    //снятие денег
    public boolean withdraw(int amount) {
        if (isBlocked || amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }

    //перевод денег на другой счет
    public boolean transfer(BankAccount otherAccount, int amount) {
        if (otherAccount == null || otherAccount == this || isBlocked ||
                otherAccount.isBlocked || amount <= 0 || amount > balance) {
            return false;
        }
        this.balance -= amount;
        otherAccount.balance += amount;
        return true;
    }

    //геттеры и сеттеры
    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "BankAccount{" +
                "ownerName='" + ownerName + '\'' +
                ", balance=" + balance +
                ", openingDate=" + openingDate.format(formatter) +
                ", isBlocked=" + isBlocked +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankAccount that = (BankAccount) o;
        return Objects.equals(accountNumber, that.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}