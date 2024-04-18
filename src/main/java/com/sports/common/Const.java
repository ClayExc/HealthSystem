package com.sports.common;

public class Const {
    public static final String CURRENT_USER = "currentUser";
    public static final String USER_NO_LOGIN = "用户未登录";

    public interface SessionExtime{
        int SESSION_EX_TIME = 60 * 30;
    }


    public static final String USERNAME = "username";
    public interface Role {
        // 普通用户
        int ROLE_CUSTOMER = 0;
        // 管理员
        int ROLE_ADMIN = 1;
    }

    public enum ProductStatusEnum {
        ON_SALE(1, "在线");
        private String value;
        private int code;

        ProductStatusEnum(int code, String value) {
            this.code = code;
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }
}
