package pl.com.seremak.simplebills.commons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TokenUser {

    private String preferredUsername;
    private String name;
    private String givenName;
    private String familyName;
}
