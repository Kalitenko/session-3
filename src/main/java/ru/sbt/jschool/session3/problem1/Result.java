package ru.sbt.jschool.session3.problem1;

/**
 */
public enum Result {
    OK,
    INSUFFICIENT_FUNDS, // недостаточно средств
    FRAUD,              // мошенник
    ALREADY_EXISTS,     // уже существует
    PAYER_NOT_FOUND,    // плательщик не найден
    RECIPIENT_NOT_FOUND // получатель не найден
}
