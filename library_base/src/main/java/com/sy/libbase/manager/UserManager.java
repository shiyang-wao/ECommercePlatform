package com.sy.libbase.manager;

import android.content.SharedPreferences;
import android.text.TextUtils;

import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import com.sy.libbase.base.BaseApplication;

import java.io.IOException;
import java.security.GeneralSecurityException;

/**
 * 用户管理类
 */
public class UserManager {
    private static final String PREFS_NAME = "user_pref"; //sp数据库名称
    private static final String KEY_TOKEN = "key_token";
    private static final String KEY_USER_ID = "key_user_id";
    private static final String KEY_NICK_NAME = "key_nick_name";
    private static final String KEY_USER_NAME = "key_user_name";
    private static final String KEY_BIO = "key_bio";
    private static final String KEY_AVATAR = "key_avatar";
    private static final String KEY_MOBILE = "key_mobile";
    private static UserManager instance;
    private SharedPreferences mPreferences;
    private static final long TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L; // 假设服务器Token有效期7天
    private static final long REFRESH_BUFFER_TIME = 30 * 60 * 1000L; // 过期前30分钟自动刷新
    private static final String KEY_TOKEN_CREATE_TIME = "token_create_time";
    private static final String KEY_TOKEN_EXPIRE_TIME = "token_expire_time";
    private static final String KEY_LAST_REFRESH_TIME = "last_refresh_time";

    //一般管理类都是单例模式，外部使用它获得的都是同一个实例对象 所以这里不能在外部new一个这个对象
    private UserManager() {
        try {
            //生成或者是获取到一个AES256数字签名算法密钥
            String masterAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);

            mPreferences = EncryptedSharedPreferences.create(
                    PREFS_NAME, masterAlias, BaseApplication.getContext(),
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, //key的加密模式
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM); //value的加密模式
        } catch (GeneralSecurityException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 单例模式  获取UserManager唯一的入口
     *
     * @return
     */
    public static UserManager getInstance() {
        if (instance == null) {
            synchronized (UserManager.class) { //保证线程安全
                if (instance == null) {
                    instance = new UserManager();
                }
            }
        }
        return instance;
    }

    /**
     * 保存token到数据库
     *
     * @param token
     */
    public void saveToken(String token) {
        mPreferences.edit().putString(KEY_TOKEN, token)
                .putLong(KEY_TOKEN_CREATE_TIME, System.currentTimeMillis())
                .putLong(KEY_TOKEN_EXPIRE_TIME, System.currentTimeMillis() + TOKEN_EXPIRE_TIME)
                .apply();
    }

    /**
     * 获取token
     *
     * @return
     */
    public String getToken() {
        String token = mPreferences.getString(KEY_TOKEN, null);
        return token;
    }

    /**
     * 获取Token状态
     */
    public TokenStatus getTokenStatus() {
        String token = getToken();
        long expireTime = mPreferences.getLong(KEY_TOKEN_EXPIRE_TIME, 0);
        long currentTime = System.currentTimeMillis();

        if (TextUtils.isEmpty(token)) {
            return TokenStatus.NOT_EXIST; // Token不存在
        }

        if (currentTime > expireTime) {
            return TokenStatus.EXPIRED; // Token已过期
        }

        // 计算剩余有效时间
        long remainingTime = expireTime - currentTime;

//        if (remainingTime < REFRESH_BUFFER_TIME) {
//            return TokenStatus.NEED_REFRESH; // 需要刷新（临近过期）
//        }

        return TokenStatus.VALID; // Token有效
    }

    /**
     * 获取Token剩余有效时间（毫秒）
     */
    public long getTokenRemainingTime() {
        long expireTime = mPreferences.getLong(KEY_TOKEN_EXPIRE_TIME, 0);
        long currentTime = System.currentTimeMillis();
        return Math.max(0, expireTime - currentTime);
    }

    /**
     * 获取Token创建时间
     */
    public long getTokenCreateTime() {
        return mPreferences.getLong(KEY_TOKEN_CREATE_TIME, 0);
    }

    /**
     * 获取Token过期时间
     */
    public long getTokenExpireTime() {
        return mPreferences.getLong(KEY_TOKEN_EXPIRE_TIME, 0);
    }

    /**
     * 检查是否有有效的Token
     */
    public boolean hasValidToken() {
        return getTokenStatus() == TokenStatus.VALID || getTokenStatus() == TokenStatus.NEED_REFRESH;
    }

    /**
     * 保存用户信息到数据库
     *
     * @param user
     */
//    public void saveUserInfo(ResUser user) {
//        UserInfo userInfo = user.getUser();
//        mPreferences.edit()
//                .putString(KEY_USER_ID, userInfo.getId())
//                .putString(KEY_NICK_NAME, userInfo.getNickname())
//                .putString(KEY_USER_NAME, userInfo.getUsername())
//                .putString(KEY_AVATAR, userInfo.getAvatar())
//                .putString(KEY_BIO, userInfo.getBio())
//                .putString(KEY_STATUS, userInfo.getStatus())
//                .putInt(KEY_FANS, user.getFans())
//                .putInt(KEY_FOLLOW, user.getFollow())
//                .putInt(KEY_MEDAL, user.getMedal())
//                .apply();
//    }

    /**
     * 获取用户信息
     *
     * @return
     */
//    public ResUser getUserInfo() {
//        String userId = mPreferences.getString(KEY_USER_ID, null);
//        String nickName = mPreferences.getString(KEY_NICK_NAME, null);
//        String userName = mPreferences.getString(KEY_USER_NAME, null);
//        String bio = mPreferences.getString(KEY_BIO, null);
//        String avatar = mPreferences.getString(KEY_AVATAR, null);
//        String status = mPreferences.getString(KEY_STATUS, null);
//        int fans = mPreferences.getInt(KEY_FANS, 0);
//        int follow = mPreferences.getInt(KEY_FOLLOW, 0);
//        int medal = mPreferences.getInt(KEY_MEDAL, 0);
//
//        ResUser user = new ResUser();
//        user.setFans(fans);
//        user.setFollow(follow);
//        user.setMedal(medal);
//
//        UserInfo userInfo = new UserInfo();
//        userInfo.setId(userId);
//        userInfo.setNickname(nickName);
//        userInfo.setUsername(userName);
//        userInfo.setBio(bio);
//        userInfo.setAvatar(avatar);
//        userInfo.setStatus(status);
//        user.setUser(userInfo);
//
//        return user;
//    }

    /**
     * 退出登录，清除用户信息
     */
    public void logout() {
//        mPreferences.edit()
//                .remove(KEY_TOKEN)
//                .remove(KEY_USER_ID)
//                .remove(KEY_NICK_NAME)
//                .remove(KEY_USER_NAME)
//                .remove(KEY_AVATAR)
//                .remove(KEY_BIO)
//                .remove(KEY_STATUS)
//                .remove(KEY_FANS)
//                .remove(KEY_FOLLOW)
//                .remove(KEY_MEDAL)
//                .apply();
        mPreferences.edit().remove(KEY_TOKEN).apply();
    }

    /**
     * 是否登录
     *
     * @return
     */
    public boolean isLogin() {
        String token = getToken();
        boolean isLogin = token != null && getTokenStatus()==TokenStatus.VALID;
        return isLogin;
    }

    /**
     * 更新资料页 只能更新这三个信息
     *
     * @param avatarUrl
     * @param nickName
     * @param bio
     */

    public void updateUserInfo(String avatarUrl, String nickName, String bio) {
        mPreferences.edit()
                .putString(KEY_NICK_NAME, nickName)
                .putString(KEY_AVATAR, avatarUrl)
                .putString(KEY_BIO, bio)
                .apply();
    }

    /**
     * Token状态枚举
     */
    public enum TokenStatus {
        NOT_EXIST,    // Token不存在
        VALID,        // Token有效
        NEED_REFRESH, // 需要刷新（临近过期）
        EXPIRED       // Token已过期
    }
}
