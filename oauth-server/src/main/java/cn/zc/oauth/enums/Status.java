package cn.zc.oauth.enums;

import lombok.Getter;

@Getter
public enum Status {

    DISABLED(0, "禁用"),
    ENABLED(1, "正常")
    ;

    private Integer val;

    private String description;

    Status(Integer val, String description) {
        this.val = val;
        this.description = description;
    }
}
