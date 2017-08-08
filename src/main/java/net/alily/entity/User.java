package net.alily.entity;

/**
 * 用户类 User
 * Created by Alan on 5/13/17.
 */
public class User {
    //用户id
    private int id;
    //用户名
    private String userName;
    //密码
    private String password;
    //用户等级
    private int level;

    public User() {

    }

    public User(String userName, String password, String level) {
        setUserName(userName);
        setPassword(password);
        setLevel(level);
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        if(id == null || id.equals("")) {
            this.id = -1;
        } else {
            this.id = Integer.parseInt(id);
        }
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if(password == null || password.equals("")) {
            this.password = null;
        } else {
//            this.password = AES.decrypt(password);
            this.password = password;
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        if(userName == null || userName.equals("")) {
            this.userName = null;
        } else {
            this.userName = userName;
        }
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(String level) {
        if(level == null || level.equals("")) {
            this.level = -1;
        } else {
            this.level = Integer.parseInt(level);
        }
    }

    @Override
    public String toString() {
        return "id: " + this.id + " userName: " + this.userName + " password: " + password;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof User) {
            User u = (User) obj;
            return u.id == this.id;
        } else {
            return false;
        }
    }
}
