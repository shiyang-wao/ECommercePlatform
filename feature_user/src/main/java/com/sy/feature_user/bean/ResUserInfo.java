package com.sy.feature_user.bean;

import java.util.List;

public class ResUserInfo {
    /**
     * id : 18
     * group_id : 1
     * username : UadTktpA
     * nickname : 用户UadTktpA
     * email :
     * mobile : 18636550503
     * avatar : /assets/addons/shopro/img/admin/default_logo.png
     * level : 1
     * gender : 0
     * birthday : null
     * bio :
     * money : 997851.00
     * commission : 0.00
     * score : 0
     * successions : 1
     * maxsuccessions : 1
     * prevtime : 2025-09-28 17:17:57
     * logintime : 2025-09-28 17:21:27
     * loginip : 60.220.40.113
     * loginfailure : 0
     * joinip : 60.220.87.119
     * jointime : 2025-09-20 01:15:37
     * token :
     * status : normal
     * verification : {"username":0,"password":1,"mobile":1}
     * parent_user_id : null
     * total_consume : 0.00
     * parent_user : null
     * third_oauth : []
     * status_text : 正常
     * gender_text : Female
     */

    private int id;
    private int group_id;
    private String username;
    private String nickname;
    private String email;
    private String mobile;
    private String avatar;
    private int level;
    private int gender;
    private Object birthday;
    private String bio;
    private String money;
    private String commission;
    private int score;
    private int successions;
    private int maxsuccessions;
    private String prevtime;
    private String logintime;
    private String loginip;
    private int loginfailure;
    private String joinip;
    private String jointime;
    private String token;
    private String status;
    private VerificationBean verification;
    private Object parent_user_id;
    private String total_consume;
    private Object parent_user;
    private String status_text;
    private String gender_text;
    private List<?> third_oauth;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroup_id() {
        return group_id;
    }

    public void setGroup_id(int group_id) {
        this.group_id = group_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getCommission() {
        return commission;
    }

    public void setCommission(String commission) {
        this.commission = commission;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getSuccessions() {
        return successions;
    }

    public void setSuccessions(int successions) {
        this.successions = successions;
    }

    public int getMaxsuccessions() {
        return maxsuccessions;
    }

    public void setMaxsuccessions(int maxsuccessions) {
        this.maxsuccessions = maxsuccessions;
    }

    public String getPrevtime() {
        return prevtime;
    }

    public void setPrevtime(String prevtime) {
        this.prevtime = prevtime;
    }

    public String getLogintime() {
        return logintime;
    }

    public void setLogintime(String logintime) {
        this.logintime = logintime;
    }

    public String getLoginip() {
        return loginip;
    }

    public void setLoginip(String loginip) {
        this.loginip = loginip;
    }

    public int getLoginfailure() {
        return loginfailure;
    }

    public void setLoginfailure(int loginfailure) {
        this.loginfailure = loginfailure;
    }

    public String getJoinip() {
        return joinip;
    }

    public void setJoinip(String joinip) {
        this.joinip = joinip;
    }

    public String getJointime() {
        return jointime;
    }

    public void setJointime(String jointime) {
        this.jointime = jointime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public VerificationBean getVerification() {
        return verification;
    }

    public void setVerification(VerificationBean verification) {
        this.verification = verification;
    }

    public Object getParent_user_id() {
        return parent_user_id;
    }

    public void setParent_user_id(Object parent_user_id) {
        this.parent_user_id = parent_user_id;
    }

    public String getTotal_consume() {
        return total_consume;
    }

    public void setTotal_consume(String total_consume) {
        this.total_consume = total_consume;
    }

    public Object getParent_user() {
        return parent_user;
    }

    public void setParent_user(Object parent_user) {
        this.parent_user = parent_user;
    }

    public String getStatus_text() {
        return status_text;
    }

    public void setStatus_text(String status_text) {
        this.status_text = status_text;
    }

    public String getGender_text() {
        return gender_text;
    }

    public void setGender_text(String gender_text) {
        this.gender_text = gender_text;
    }

    public List<?> getThird_oauth() {
        return third_oauth;
    }

    public void setThird_oauth(List<?> third_oauth) {
        this.third_oauth = third_oauth;
    }

    public static class VerificationBean {
        /**
         * username : 0
         * password : 1
         * mobile : 1
         */

        private int username;
        private int password;
        private int mobile;

        public int getUsername() {
            return username;
        }

        public void setUsername(int username) {
            this.username = username;
        }

        public int getPassword() {
            return password;
        }

        public void setPassword(int password) {
            this.password = password;
        }

        public int getMobile() {
            return mobile;
        }

        public void setMobile(int mobile) {
            this.mobile = mobile;
        }
    }
}
