package pl.com.seremak.simplebills.commons.converter;

import pl.com.seremak.simplebills.commons.dto.http.DepositDto;
import pl.com.seremak.simplebills.commons.dto.http.TransactionDto;
import pl.com.seremak.simplebills.commons.model.Deposit;

public class DepositConverter {

    public static Deposit toDeposit(final String username, final String depositName, final DepositDto depositDto) {
        return toDeposit(username, null, depositName, depositDto);
    }

    public static Deposit toDeposit(final String username, final Integer transactionNumber, final DepositDto depositDto) {
        return toDeposit(username, transactionNumber, depositDto.getName(), depositDto);
    }

    private static Deposit toDeposit(final String username,
                                     final Integer transactionNumber,
                                     final String depositName,
                                     final DepositDto depositDto) {
        return Deposit.builder()
                .username(username)
                .name(depositName)
                .value(depositDto.getValue())
                .depositType(depositDto.getDepositType())
                .bankName(depositDto.getBankName())
                .durationInMonths(depositDto.getDurationInMonths())
                .annualInterestRate(depositDto.getAnnualInterestRate())
                .transactionNumber(transactionNumber)
                .build();
    }

    public static DepositDto toDepositDto(final Deposit deposit) {
        return DepositDto.builder()
                .name(deposit.getName())
                .value(deposit.getValue())
                .depositType(deposit.getDepositType())
                .bankName(deposit.getBankName())
                .durationInMonths(deposit.getDurationInMonths())
                .annualInterestRate(deposit.getAnnualInterestRate())
                .build();
    }

    public static Deposit toDeposit(final TransactionDto transactionDto) {
        return Deposit.builder()
                .transactionNumber(transactionDto.getTransactionNumber())
                .value(transactionDto.getAmount())
                .build();
    }
}
