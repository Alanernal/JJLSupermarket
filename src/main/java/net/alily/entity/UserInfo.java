package net.alily.entity;

/**
 * 用户信息类
 * Created by Alan on 5/27/17.
 */
public class UserInfo {
    private User user;
    private UserDetailedInfo userDetailedInfo;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserDetailedInfo getUserDetailedInfo() {
        return userDetailedInfo;
    }

    public void setUserDetailedInfo(UserDetailedInfo userDetailedInfo) {
        this.userDetailedInfo = userDetailedInfo;
    }
}
