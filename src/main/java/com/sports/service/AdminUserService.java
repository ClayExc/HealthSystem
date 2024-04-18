package com.sports.service;

import com.sports.common.ServerResponse;
import com.sports.entity.AdminUser;

public interface AdminUserService {

    ServerResponse<AdminUser> login(String username, String password);
    int userDelete(String userId);
}