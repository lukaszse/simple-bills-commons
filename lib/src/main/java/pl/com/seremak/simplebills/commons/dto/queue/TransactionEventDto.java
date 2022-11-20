package pl.com.seremak.simplebills.commons.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEventDto implements Serializable {

    private String username;
    private String categoryName;
    private BigDecimal amount;
    private ActionType type;
    private Instant date;
}
