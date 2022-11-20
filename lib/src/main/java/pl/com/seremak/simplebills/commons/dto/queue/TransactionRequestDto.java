package pl.com.seremak.simplebills.commons.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class TransactionRequestDto implements Serializable {

    private ActionType requestType;
    private String user;
    private Integer transactionNumber;
    private String type;
    private LocalDate date;
    private String description;
    private String category;
    @Positive(message = "Amount cannot be negative") private BigDecimal amount;
}
