package com.lsh.mustardtest.msg;

import com.lsh.mustardtest.pojo.User;

/**
 * 刘森华
 * 2019/04/18
 */

public class AccountMsg {
    private User user;
    private String message;
    private boolean flag;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }


}
