package cn.zc.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("oauth_client")
public class OAuthClient {

    @TableId
    private Long id;

    private String clientId;

    private String resourceIds;

    private String clientSecret;

    private String scope;

    private String authorizedGrantTypes;

    private String registeredRedirectUri;

    private Integer accessTokenValiditySeconds;

    private Integer refreshTokenValiditySeconds;

    private String additionalInformation;

    private Integer isAutoApprove;

}
