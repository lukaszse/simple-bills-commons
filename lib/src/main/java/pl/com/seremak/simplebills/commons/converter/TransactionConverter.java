package pl.com.seremak.simplebills.commons.converter;

import org.apache.commons.lang3.StringUtils;
import pl.com.seremak.simplebills.commons.dto.http.TransactionDto;
import pl.com.seremak.simplebills.commons.dto.queue.ActionType;
import pl.com.seremak.simplebills.commons.dto.queue.TransactionEventDto;
import pl.com.seremak.simplebills.commons.dto.queue.TransactionRequestDto;
import pl.com.seremak.simplebills.commons.model.Category;
import pl.com.seremak.simplebills.commons.model.Deposit;
import pl.com.seremak.simplebills.commons.model.Transaction;
import pl.com.seremak.simplebills.commons.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;

import static pl.com.seremak.simplebills.commons.model.Transaction.Type.EXPENSE;
import static pl.com.seremak.simplebills.commons.model.Transaction.Type.valueOf;
import static pl.com.seremak.simplebills.commons.utils.DateUtils.toInstantUTC;

public class TransactionConverter {


    public static Transaction toTransaction(final TransactionRequestDto transactionRequestDto) {
        final Transaction.TransactionBuilder transactionBuilder = Transaction.builder()
                .user(transactionRequestDto.getUser())
                .type(valueOf(transactionRequestDto.getType().toUpperCase()))
                .transactionNumber(transactionRequestDto.getTransactionNumber())
                .description(transactionRequestDto.getDescription())
                .amount(normalizeAmount(transactionRequestDto.getAmount(), transactionRequestDto.getType()))
                .category(transactionRequestDto.getCategory());
        toInstantUTC(transactionRequestDto.getDate())
                .ifPresent(transactionBuilder::date);
        return transactionBuilder.build();
    }

    public static TransactionRequestDto toTransactionRequestDto(final Deposit deposit, final ActionType requestType) {
        return TransactionRequestDto.builder()
                .requestType(requestType)
                .user(deposit.getUsername())
                .category(Category.Type.ASSET.toString())
                .type(EXPENSE.toString())
                .amount(deposit.getValue())
                .date(LocalDate.now())
                .description(prepareDescription(deposit))
                .build();
    }

    public static Transaction toTransaction(final String username, final TransactionDto transactionDto) {
        return toTransaction(username, transactionDto.getTransactionNumber(), transactionDto);
    }

    public static Transaction toTransaction(final String username,
                                            final Integer transactionNumber,
                                            final TransactionDto transactionDto) {
        final Transaction.TransactionBuilder transactionBuilder = Transaction.builder()
                .user(username)
                .type(valueOf(transactionDto.getType().toUpperCase()))
                .transactionNumber(transactionNumber)
                .description(transactionDto.getDescription())
                .amount(normalizeAmount(transactionDto.getAmount(), transactionDto.getType()))
                .category(transactionDto.getCategory());
        toInstantUTC(transactionDto.getDate())
                .ifPresent(transactionBuilder::date);
        return transactionBuilder.build();
    }

    public static TransactionDto toTransactionDto(final Transaction transaction) {
        final TransactionDto.TransactionDtoBuilder transactionDtoBuilder = TransactionDto.builder()
                .type(transaction.getType().toString().toUpperCase())
                .transactionNumber(transaction.getTransactionNumber())
                .description(transaction.getDescription())
                .amount(normalizeAmount(transaction.getAmount(), transaction.getType()))
                .category(transaction.getCategory());
        DateUtils.toLocalDate(transaction.getDate())
                .ifPresent(transactionDtoBuilder::date);
        return transactionDtoBuilder.build();
    }

    public static TransactionEventDto toTransactionDto(final Transaction transaction,
                                                       final ActionType actionType) {
        return toTransactionDto(transaction, actionType, transaction.getAmount());
    }

    public static TransactionEventDto toTransactionDto(final Transaction transaction,
                                                       final ActionType actionType,
                                                       final BigDecimal amountDiff) {
        return TransactionEventDto.builder()
                .username(transaction.getUser())
                .categoryName(transaction.getCategory())
                .type(actionType)
                .amount(normalizeAmount(amountDiff, transaction.getType()))
                .date(transaction.getDate())
                .build();
    }

    private static BigDecimal normalizeAmount(final BigDecimal amount, final Transaction.Type transactionType) {
        return EXPENSE.equals(transactionType) ?
                amount.abs().negate() : amount.abs();
    }

    private static BigDecimal normalizeAmount(final BigDecimal amount, final String transactionTypeStr) {
        final Transaction.Type transactionType = valueOf(transactionTypeStr);
        return normalizeAmount(amount, transactionType);
    }

    private static String prepareDescription(final Deposit deposit) {
        final String description = StringUtils.isBlank(deposit.getBankName()) ?
                "Deposit: `%s`".formatted(deposit.getName()) :
                "Deposit: `%s`in %s".formatted(deposit.getName(), deposit.getBankName());
        return description.substring(0, 1).toUpperCase() + description.substring(1);
    }
}
