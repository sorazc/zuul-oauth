package cn.zc.oauth.model;

import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

@Setter
public class SimpleClientDetails implements ClientDetails {

    private String clientId;

    private Set<String> resourceIds;

    private String clientSecret;

    private Set<String> scope;

    private Set<String> authorizedGrantTypes;

    private Set<String> registeredRedirectUri;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private Map<String, Object> additionalInformation;

    private Integer isAutoApprove;

    private Collection<GrantedAuthority> authorities;

    @Override
    public String getClientId() {
        return this.clientId;
    }

    @Override
    public String getClientSecret() {
        return this.clientSecret;
    }

    @Override
    public Set<String> getResourceIds() {
        return this.resourceIds;
    }

    @Override
    public boolean isSecretRequired() {
        return true;
    }

    @Override
    public boolean isScoped() {
        return false;
    }

    @Override
    public Set<String> getScope() {
        return this.scope;
    }

    @Override
    public Set<String> getAuthorizedGrantTypes() {
        return this.authorizedGrantTypes;
    }

    @Override
    public Set<String> getRegisteredRedirectUri() {
        return this.registeredRedirectUri;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public Integer getAccessTokenValiditySeconds() {
        return this.accessTokenValiditySeconds;
    }

    @Override
    public Integer getRefreshTokenValiditySeconds() {
        return this.refreshTokenValiditySeconds;
    }

    @Override
    public boolean isAutoApprove(String s) {
        return this.isAutoApprove == 1;
    }

    @Override
    public Map<String, Object> getAdditionalInformation() {
        return this.additionalInformation;
    }
}
