package pl.com.seremak.simplebills.commons.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.math.BigDecimal;

@Data
@EqualsAndHashCode(callSuper = true)
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Deposit extends Asset {

    public enum DepositType {
        PERPETUAL, TERM
    }

    private DepositType depositType;

    private String bankName;

    private Integer durationInMonths;

    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal annualInterestRate;

    private Integer transactionNumber;
}
