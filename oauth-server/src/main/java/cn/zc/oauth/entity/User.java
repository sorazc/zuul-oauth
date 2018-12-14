package cn.zc.oauth.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@TableName("user")
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@TableId
	private Long id;

	private String username;

	private String salt;

	private String password;

	private String displayName;

	private Integer type;

	private String nickname;

	private String mobile;

	private String email;

	private Integer status;

	private Integer isRealName;

	private Integer isIndustry;

	private LocalDateTime createTime;

	private LocalDateTime editTime;

}
