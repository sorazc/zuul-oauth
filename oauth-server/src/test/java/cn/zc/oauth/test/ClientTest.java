package cn.zc.oauth.test;

import cn.zc.oauth.mapper.OAuthClientMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ClientTest {

    @Autowired
    private OAuthClientMapper oAuthClientMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        oAuthClientMapper.selectList(new QueryWrapper<>());
    }

}
