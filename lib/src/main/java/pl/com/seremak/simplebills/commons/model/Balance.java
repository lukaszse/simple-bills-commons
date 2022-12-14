package pl.com.seremak.simplebills.commons.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@Document
@NoArgsConstructor
@AllArgsConstructor
public class Balance extends VersionedEntity {

    @Id
    @NotBlank(message = "Balance user cannot be blank")
    private String username;

    @NotNull
    @Field(targetType = FieldType.DECIMAL128)
    private BigDecimal balance;
}
