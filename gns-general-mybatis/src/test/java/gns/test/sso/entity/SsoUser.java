package gns.test.sso.entity;

import java.util.Date;

public class SsoUser {
    private Integer userId;

    private String userName;

    private String userPasswordSalt;

    private String userPassword;

    private Date userCreateTs;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPasswordSalt() {
        return userPasswordSalt;
    }

    public void setUserPasswordSalt(String userPasswordSalt) {
        this.userPasswordSalt = userPasswordSalt;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public Date getUserCreateTs() {
        return userCreateTs;
    }

    public void setUserCreateTs(Date userCreateTs) {
        this.userCreateTs = userCreateTs;
    }
}