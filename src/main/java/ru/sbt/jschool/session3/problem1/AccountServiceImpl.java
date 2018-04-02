package ru.sbt.jschool.session3.problem1;

import java.util.*;

/**
 */
public class AccountServiceImpl implements AccountService {
    protected FraudMonitoring fraudMonitoring;

    private Map<Long, Account> accounts = new HashMap<>();
    private Set<Long> payments = new HashSet<>();

    public AccountServiceImpl(FraudMonitoring fraudMonitoring) {
        this.fraudMonitoring = fraudMonitoring;
    }

    @Override public Result create(long clientID, long accountID, float initialBalance, Currency currency) {
        // проверка на мошенника
        if(fraudMonitoring.check(clientID))
            return Result.FRAUD;

        // проверка на наличие существующего счета
        if(accounts.containsKey(accountID))
            return Result.ALREADY_EXISTS;

        Account account = new Account(clientID, accountID, currency, initialBalance);

        accounts.put(accountID, account);
        return Result.OK;
    }

    @Override public List<Account> findForClient(long clientID) {

        List<Account> finForClientList = new ArrayList<>();

        List<Account> accountList = new ArrayList<>(accounts.values());

        for(Account x : accountList)
            if(x.getClientID() == clientID)
                finForClientList.add(x);

        return finForClientList;
    }

    @Override public Account find(long accountID) {
        return accounts.get(accountID);
    }

    @Override public Result doPayment(Payment payment) {

        // проверка на мошенника
        if(fraudMonitoring.check(payment.getPayerID()) || fraudMonitoring.check(payment.getRecipientID()))
            return Result.FRAUD;

        // проверка существования операции
        if(payments.contains(payment.getOperationID()))
            return Result.ALREADY_EXISTS;
        else payments.add(payment.getOperationID());

        // проверка плательщика
        if(findForClient(payment.getPayerID()).isEmpty() || find(payment.getPayerAccountID()) == null)
            return Result.PAYER_NOT_FOUND;

        // проверка получателя
        if(findForClient(payment.getRecipientID()).isEmpty() || find(payment.getRecipientAccountID()) == null)
            return Result.RECIPIENT_NOT_FOUND;

        // проверка баланса и проведение платежа
        if(accounts.get(payment.getPayerAccountID()).getBalance() < payment.getAmount())
            return Result.INSUFFICIENT_FUNDS;
        else makePayment(payment);

        return Result.OK;
    }

    private void makePayment(Payment payment){

        float amount = payment.getAmount();

        Account payerAccountID = accounts.get(payment.getPayerAccountID());
        Account recipientAccountID = accounts.get(payment.getRecipientAccountID());

        Currency payerCurrency = payerAccountID.getCurrency();
        Currency recipientCurrency = recipientAccountID.getCurrency();

        // списание средств
        payerAccountID.setBalance(payerAccountID.getBalance() - amount);

        // конвертация валюты
        if(payerCurrency != recipientCurrency)
            amount = payerCurrency.to(amount, recipientCurrency);

        // перевод средств
        recipientAccountID.setBalance(recipientAccountID.getBalance() + amount);

    }
}
