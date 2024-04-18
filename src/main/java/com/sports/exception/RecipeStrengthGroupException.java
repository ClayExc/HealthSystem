package com.sports.exception;

import com.sports.common.ResponseCode;

/**
 * @Author: wukaizhen
 * @Date: 2023/11/12 15:07
 * 好好学习，天天向上
 */
public class RecipeStrengthGroupException extends CustomException {
    public RecipeStrengthGroupException(ResponseCode responseCode) {
        super(responseCode);
    }

    public RecipeStrengthGroupException(ResponseCode responseCode, Object... args) {
        super(responseCode, args);
    }
}
