package pl.com.seremak.simplebills.commons.dto.http;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.springframework.lang.Nullable;

import javax.validation.constraints.Pattern;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUser {

    @Nullable
    @Pattern(regexp = "^\\S+$", message = "Username must contain only non-whitespace characters and cannot be empty")
    private String preferredUsername;

    @Nullable
    @Pattern(regexp = "^\\S+$", message = "Username must contain only non-whitespace characters and cannot be empty")
    private String name;

    @Nullable
    private String givenName;

    @Nullable
    private String familyName;
}
