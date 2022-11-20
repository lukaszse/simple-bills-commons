package pl.com.seremak.simplebills.commons.converter;

import pl.com.seremak.simplebills.commons.dto.http.DepositDto;
import pl.com.seremak.simplebills.commons.model.Deposit;

public class DepositConverter {

    public static Deposit toDeposit(final String username, final String depositName, final DepositDto depositDto) {
        return toDeposit(username, null, depositName, depositDto);
    }

    public static Deposit toDeposit(final String username, final Integer transactionNumber, final DepositDto depositDto) {
        return toDeposit(username, transactionNumber, depositDto.getName(), depositDto);
    }

    public static Deposit toDeposit(final String username,
                                    final Integer transactionNumber,
                                    final String depositName,
                                    final DepositDto depositDto) {
        final Deposit deposit = new Deposit();
        deposit.setUsername(username);
        deposit.setName(depositName);
        deposit.setValue(depositDto.getValue());
        deposit.setDepositType(depositDto.getDepositType());
        deposit.setBankName(depositDto.getBankName());
        deposit.setDurationInMonths(depositDto.getDurationInMonths());
        deposit.setAnnualInterestRate(depositDto.getAnnualInterestRate());
        deposit.setTransactionNumber(transactionNumber);
        return deposit;
    }

    public static DepositDto toDepositDto(final Deposit deposit) {
        final DepositDto depositDto = new DepositDto();
        depositDto.setName(deposit.getName());
        depositDto.setValue(deposit.getValue());
        depositDto.setDepositType(deposit.getDepositType());
        depositDto.setBankName(deposit.getBankName());
        depositDto.setDurationInMonths(deposit.getDurationInMonths());
        depositDto.setAnnualInterestRate(deposit.getAnnualInterestRate());
        return depositDto;
    }
}
