package pl.com.seremak.simplebills.commons.dto.http;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {

    @Nullable
    @Pattern(regexp = "^[a-zA-Z]+\\w{1,19}", message = "Login must start with a letter and has 2 - 20 word characters (digits, letters, _)")
    private String username;

    @NotBlank(message = "Name of Category cannot be blank")
    private String name;

    @NotNull(message = "Transaction type cannot be null")
    private String transactionType;

    @Nullable
    private BigDecimal limit;
}
