package com.yue_health.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.Where;
import com.yue_health.entity.User;
import com.yue_health.util.DatabaseHelper;

import java.sql.SQLException;
import java.util.List;

/**
 * 用户信息的增删改查
 */
public class UserDao {

    private static final String TAG = "UserDao";

    private static UserDao instance;

    private Context context;

    private DatabaseHelper helper;

    private Dao<User, Integer> userIntegerDao;

    private UserDao(Context context) {
        this.context = context;
        try {
            helper = DatabaseHelper.getHelper(context);
            userIntegerDao = (Dao<User, Integer>) helper.getDao(User.class);
        } catch (SQLException e) {
            Log.d(TAG, "UserDao: 创建 UserDao 对象出现异常");
            e.printStackTrace();
        }
    }

    /**
     * 单例模式获取UserDao 对象
     *
     * @param context
     * @return
     */
    public static UserDao getInstance(Context context) {
        if (instance == null) {
            instance = new UserDao(context);
        }
        return instance;
    }

    /**
     * 添加单个用户信息
     *
     * @param user
     */
    public int add(User user) {
        int affectColumn = 0;
        if (user != null) {
            try {
                affectColumn = userIntegerDao.create(user);
            } catch (SQLException e) {
                Log.d(TAG, "add: 添加用户:" + user.toString() + "出现异常");
                e.printStackTrace();
            }
        }
        return affectColumn;
    }

    /**
     * 添加多个用户信息
     *
     * @param users
     */
    public void add(List<User> users) {
        if (users != null && !users.isEmpty()) {
            for (User user : users) {
                add(user);
            }
        }
    }

    public static final int REGIST_FAIL = 0;//注册失败
    public static final int PHONE_EXIST = 1; //电话号码已注册
    public static final int EMAIL_EXIST = 2;//邮箱已注册
    public static final int REGIST_SUCESS = 3;//注册成功

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    public int createUser(User user) {
        boolean phoneExist = false;
        boolean emailExist = false;
        if (user != null) {
            if (queryByPhoneOrEmail(user.getPhone(), null) != null) {
                phoneExist = true;
                return PHONE_EXIST;
            }
            if (queryByPhoneOrEmail(null, user.getEmail()) != null) {
                emailExist = true;
                return EMAIL_EXIST;
            }
            if (!phoneExist && !emailExist && add(user) > 0) {
                return REGIST_SUCESS;
            }
        }
        return REGIST_FAIL;
    }


    /**
     * 删除用户信息
     *
     * @param userId
     */
    public void delete(int userId) {
        try {
            userIntegerDao.deleteById(userId);
        } catch (SQLException e) {
            Log.d(TAG, "delete: 删除用户:" + userId + "出现异常");
            e.printStackTrace();
        }
    }

    /**
     * 修改用户对象
     *
     * @param user
     */
    public void update(User user) {
        if (user != null) {
            try {
                userIntegerDao.update(user);
            } catch (SQLException e) {
                Log.d(TAG, "update: 修改用户:" + user.toString() + "出现异常");
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过用户密码和账号查找用户信息
     *
     * @param account
     * @param password
     */
    public User queryByAccountAndPassword(String account, int password) {
        User user = null;
        if (account != null) {
            try {
                Where<User, Integer> where = userIntegerDao.queryBuilder().where().eq("account", account).and().eq("passwd", password);
                user = where.queryForFirst();
            } catch (SQLException e) {
                Log.d(TAG, "UserDao: 用户" + account + "不存在");
                e.printStackTrace();
            }
        }

        return user;
    }


    /**
     * 通过电话号码或邮箱查找用户信息
     *
     * @param phone
     * @param email
     * @return
     */
    public User queryByPhoneOrEmail(String phone, String email) {
        phone = (phone == null ? "" : phone);
        email = (email == null ? "" : email);
        User user = null;
        try {
            Where<User, Integer> where = userIntegerDao.queryBuilder().where().eq("phone", phone).or().eq("email", email);
            user = where.queryForFirst();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    /**
     * 通过电话号码和密码查找用户信息
     *
     * @param phone
     * @param passwd
     * @return
     */
    public User queryByPhoneAndPasswd(String phone, int passwd) {
        User user = null;
        try {
            Where<User, Integer> where = userIntegerDao.queryBuilder().where().eq("phone", phone)
                    .and().eq("passwd", passwd);
            user = where.queryForFirst();
        } catch (SQLException e) {
            Log.d(TAG, "queryByPhoneAndPasswd: " + phone + "登录失败");
        }
        return user;
    }

    /**
     * 通过邮箱和密码查找用户信息
     *
     * @param email
     * @param passwd
     * @return
     */
    public User queryByEmailAndPasswd(String email, int passwd) {
        User user = null;
        try {
            Where<User, Integer> where = userIntegerDao.queryBuilder().where()
                    .eq("email", email)
                    .and().eq("passwd", passwd);
            user = where.queryForFirst();
        } catch (SQLException e) {
            Log.d(TAG, "queryByEmailAndPasswd: " + email + "登录失败");
        }
        return user;
    }

    /**
     * 通过Id查找用户信息
     *
     * @param userId
     * @return
     */
    public User queryById(int userId) {
        User user = null;
        try {
            user = userIntegerDao.queryForId(userId);
        } catch (SQLException e) {
            Log.d(TAG, "UserDao: 用户id:" + userId + "不存在");
            e.printStackTrace();
        }
        return user;

    }

    /**
     * 通过用户账号和密码查找用户信息
     *
     * @param account
     * @return
     */
    public User queryByAccountAndPasswd(String account, int passwd) {
        User user = null;
        try {
            Where<User, Integer> where = userIntegerDao.queryBuilder().where()
                    .eq("account", account)
                    .and().eq("passwd", passwd);
            user = where.queryForFirst();
        } catch (SQLException e) {
            Log.d(TAG, "queryByAccount: 用户:" + account + "不存在");
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 缓存正在登录的用户信息
     *
     * @param user
     */
    public void saveLoginUser(User user) {
        if (user != null) {
            SharedPreferences.Editor editor = context.getSharedPreferences("login_user",
                    Context.MODE_PRIVATE).edit();
            editor.putInt("user_id", user.getUserId());
            editor.putString("user_account", user.getAccount());
            editor.putString("user_name", user.getName());
            editor.putString("user_nickname", user.getNickname());
            editor.putString("user_birthday", user.getBirthday());
            editor.putInt("user_gender", user.getGender());
            editor.putString("user_phone", user.getPhone());
            editor.putString("user_email", user.getEmail());
            editor.putString("user_occupation", user.getOccupation());
            editor.apply();
        }
    }

    /**
     * 获取正在登录的用户信息
     *
     * @return
     */
    public User getLoginUser() {
        SharedPreferences preferences = context.getSharedPreferences("login_user", Context.MODE_PRIVATE);
        User user = null;

        int userId = preferences.getInt("user_id", 0);
        if (userId == 0) {
            return user;
        }
        user = new User();
        user.setUserId(userId);
        user.setAccount(preferences.getString("user_account", ""));
        user.setName(preferences.getString("user_name", ""));
        user.setNickname(preferences.getString("user_nickname", ""));
        user.setBirthday(preferences.getString("user_birthday", ""));
        user.setGender(preferences.getInt("user_gender", 1));
        user.setPhone(preferences.getString("user_phone", ""));
        user.setEmail(preferences.getString("user_email", ""));
        user.setOccupation(preferences.getString("user_occupation", ""));
        return user;

    }

    /**
     * 删除在登录的用户信息
     */
    public void deleteLoginUser() {
        SharedPreferences.Editor editor = context.getSharedPreferences("login_user",
                Context.MODE_PRIVATE).edit();
        editor.clear().clear();
    }
}
