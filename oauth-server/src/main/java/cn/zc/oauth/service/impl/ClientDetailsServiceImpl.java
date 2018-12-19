package cn.zc.oauth.service.impl;

import cn.zc.oauth.entity.OAuthClient;
import cn.zc.oauth.model.SimpleClientDetails;
import cn.zc.oauth.service.OAuthClientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class ClientDetailsServiceImpl implements ClientDetailsService {

    @Autowired
    private OAuthClientService oAuthClientService;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        log.info(clientId);
        OAuthClient oAuthClient = oAuthClientService.getOne(new QueryWrapper<OAuthClient>()
                .eq("client_id", clientId));

        if (oAuthClient == null) {
            return null;
        }

        SimpleClientDetails simpleClientDetails = new SimpleClientDetails();
        simpleClientDetails.setClientId(oAuthClient.getClientId());
        simpleClientDetails.setClientSecret(oAuthClient.getClientSecret());
        Set<String> scopes = new HashSet<String>(){{
            for (String scope : oAuthClient.getScope().split(",")) {
                this.add(scope);
            }
        }};
        simpleClientDetails.setScope(scopes);
        Set<String> authorizedGrantTypes = new HashSet<String>(){{
            for (String type : oAuthClient.getAuthorizedGrantTypes().split(",")) {
                this.add(type);
            }
        }};
        simpleClientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
        simpleClientDetails.setAccessTokenValiditySeconds(oAuthClient.getAccessTokenValiditySeconds());
        simpleClientDetails.setRefreshTokenValiditySeconds(oAuthClient.getRefreshTokenValiditySeconds());
        simpleClientDetails.setIsAutoApprove(oAuthClient.getIsAutoApprove());

        simpleClientDetails.setAuthorities(new LinkedList<>());

        return simpleClientDetails;
    }
}
