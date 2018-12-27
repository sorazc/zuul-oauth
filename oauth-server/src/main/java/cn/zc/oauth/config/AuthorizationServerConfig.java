package cn.zc.oauth.config;

import cn.zc.oauth.model.SimpleUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    @Qualifier("clientDetailsServiceImpl")
    private ClientDetailsService clientDetailsService;

    @Autowired
    @Qualifier("userDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


    /**
     * defines the security constraints on the token endpoint.
     * change access rule of both the /oauth/check_token endpoint and the /oauth/token_key endpoint
     * (default is "denyAll()")
     */
    @Override
    public void configure(final AuthorizationServerSecurityConfigurer oauthServer) {
        oauthServer.tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()");
    }

    @Override
    public void configure(final ClientDetailsServiceConfigurer clients) throws Exception {
        clients.withClientDetails(clientDetailsService);
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints
                //AuthenticationManage 这个 Bean 来源于 WebSecurityConfigurerAdapter 中的配置
                //配置了这个 Bean 才会开启密码类型的验证。
                .authenticationManager(authenticationManager)
                //用来读取验证用户的信息
                .userDetailsService(userDetailsService)
                //token储存方式
                //.tokenStore(redisTokenStore())
                .tokenStore(jwtTokenStore())
                .tokenEnhancer(jwtAccessTokenConverter())
        ;
    }

    //@Bean
    public TokenStore redisTokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        tokenStore.setPrefix("zc_");
        return tokenStore;
    }

    @Bean
    public TokenStore jwtTokenStore() {
        JwtTokenStore jwtTokenStore = new JwtTokenStore(jwtAccessTokenConverter());
        return jwtTokenStore;
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
        jwtAccessTokenConverter.setSigningKey("zc");
        return jwtAccessTokenConverter;
    }

    //@Bean
    public TokenEnhancer tokenEnhancer() {
        return (oAuth2AccessToken, oAuth2Authentication) -> {
            final Map<String, Object> additionalInfo = new HashMap<>(2);

            SimpleUserDetails userDetails = (SimpleUserDetails) oAuth2Authentication.getUserAuthentication().getPrincipal();

            additionalInfo.put("userId", userDetails.getId());
            ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(additionalInfo);
            return oAuth2AccessToken;
        };
    }

}
