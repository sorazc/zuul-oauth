package com.ingkey.platform.ui.util;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Created by zc on 2019/3/18.
 */
public class AuthUtils {

    public static Object getPrinciple() {
        return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
