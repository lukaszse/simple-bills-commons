package pl.com.seremak.simplebills.commons.dto.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;
import pl.com.seremak.simplebills.commons.model.Category;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryEventDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotNull(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Name of Category cannot be blank")
    private String categoryName;

    @NotNull(message = "Action type cannot be null")
    private ActionType actionType;

    @NotNull(message = "Transaction type cannot be null")
    private Category.TransactionType transactionType;

    @Nullable
    private BigDecimal limit;

    @Nullable
    private BigDecimal usageOfLimit;

    @Nullable
    private String replacementCategoryName;
}
