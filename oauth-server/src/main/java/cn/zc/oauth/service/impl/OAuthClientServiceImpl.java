package cn.zc.oauth.service.impl;

import cn.zc.oauth.entity.OAuthClient;
import cn.zc.oauth.mapper.OAuthClientMapper;
import cn.zc.oauth.service.OAuthClientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class OAuthClientServiceImpl extends ServiceImpl<OAuthClientMapper, OAuthClient> implements OAuthClientService {
}
