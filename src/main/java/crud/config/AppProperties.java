package crud.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties
public class AppProperties {

    @Getter
    private final Auth auth = new Auth();

    @Getter
    private final OAuth2 oauth2 = new OAuth2();

    public static class Auth {

        @Getter
        @Setter
        private String tokenSecret;

        @Getter
        @Setter
        private long tokenExpirationMsec;
    }

    public static final class OAuth2 {

        @Getter
        private List<String> authorizedRedirectUris = new ArrayList<>();

        public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
            this.authorizedRedirectUris = authorizedRedirectUris;
            return this;
        }
    }
}
