package pl.com.seremak.simplebills.commons.utils;

import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriUtils;
import org.yaml.snakeyaml.util.UriEncoder;

import java.net.URI;

public class EndpointUtils {

    public static ResponseEntity<String> prepareCreatedResponse(final String uriPattern, final String identifier) {
        return prepareCreatedResponse(uriPattern, identifier, identifier);
    }

    public static <T> ResponseEntity<T> prepareCreatedResponse(final String uriPattern, final String identifier, final T body) {
        final String format = String.format(uriPattern, identifier);
        final URI uri = URI.create(UriUtils.encode(format, "UTF-8"));
        return ResponseEntity.created(uri)
                .body(body);
    }

    public static String decodeUriParam(final String uriParam) {
        return UriUtils.decode(uriParam, "UTF-8");
    }
}
