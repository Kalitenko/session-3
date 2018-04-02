package ru.sbt.jschool.session3.problem1;

import java.util.Collection;

/**
 */
// функции
public interface AccountService {
    // создание учетной записи
    Result create(long clientID, long accountID, float initialBalance, Currency currency);
    // поиск учетных записей для клиента
    Collection<Account> findForClient(long clientID);
    // поиск учетной записи
    Account find(long accountID);
    // совершить платеж
    Result doPayment(Payment payment);
}
