package crud.security.oauth.user;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public abstract class OAuth2UserInfo {

    @Getter
    @NonNull
    protected Map<String, Object> attributes;

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();
}