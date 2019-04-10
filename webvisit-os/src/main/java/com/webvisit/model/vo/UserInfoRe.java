package com.webvisit.model.vo;

import java.util.Arrays;

/**
 * @author zening.zhu
 * @version v1.0
 * @date 2019/4/10
 */
public class UserInfoRe {

    private String[] roles;

    private String username;

    private String avatar;

    private String introduction;

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "UserInfoRe{" +
                "roles=" + Arrays.toString(roles) +
                ", username='" + username + '\'' +
                ", avatar='" + avatar + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
