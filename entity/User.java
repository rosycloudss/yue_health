package com.yue_health.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.yue_health.form.RegistForm;

import java.util.Date;

/**
 * 用户信息
 */
@DatabaseTable(tableName = "tb_user")
public class User {

    @DatabaseField(generatedId = true, columnName = "user_id")
    private int userId;//用户编号
    @DatabaseField(columnName = "account")
    private String account;//账号
    @DatabaseField(columnName = "name")
    private String name = "";//用户姓名
    @DatabaseField(columnName = "birthday")
    private String birthday;
    @DatabaseField(columnName = "nickname")
    private String nickname;
    //    @DatabaseField(columnName = "age")
//    private int age = 0;//用户年龄 (0-120)
    @DatabaseField(columnName = "gender")
    private int gender = 1;//性别( 1.男 或 2.女)
    @DatabaseField(columnName = "occupation")
    private String occupation = "";//职业
    @DatabaseField(columnName = "phone")
    private String phone;
    @DatabaseField(columnName = "email")
    private String email = "";//邮箱
    @DatabaseField(columnName = "physical_examination_times")
    private int physicalExaminationTimes = 0;//体检次数
    @DatabaseField(columnName = "passwd")
    private int password = 123456;//密码 哈希值 默认为 123456
    @DatabaseField(columnName = "role")
    private int role = 1; //用户类型 (1.普通用户 ,2.管理员)

    public static User createUser(RegistForm registForm) {
        if (registForm != null) {
            User user = new User();
            String account = new Date().getTime() + "";
            user.setAccount(account.substring(account.length() - 10, account.length() - 1));
//            user.setAge(registForm.getAge());
            user.setEmail(registForm.getEmail());
            user.setGender(registForm.getGender());
            user.setName(registForm.getName());
            user.setOccupation(registForm.getOccupation());
            user.setPassword(registForm.getPasswd());
            user.setBirthday(registForm.getBirthday());
            user.setPhone(registForm.getPhone());
            user.setNickname(registForm.getNickname());
            return user;
        }
        return null;


    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        //如果 age < 0 则 age 设为 0 ,同样 age > 150 age 设为 150
//        if(age < 0){
//            this.age = 0;
//        }else if(age > 150){
//            age = 150;
//        }else {
//            this.age = age;
//        }
//    }

    public String getGenderString() {
        return gender == 1 ? "男" : "女";
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        if (gender == 1 || gender == 2) {
            this.gender = gender;
        }

    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhysicalExaminationTimes() {
        return physicalExaminationTimes;
    }

    public void setPhysicalExaminationTimes(int physicalExaminationTimes) {
        this.physicalExaminationTimes = physicalExaminationTimes;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password != null) {
            this.password = password.hashCode();
        }
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", account='" + account + '\'' +
                ", name='" + name + '\'' +
                ", birthday='" + birthday + '\'' +
                ", nickname='" + nickname + '\'' +
//                ", age=" + age +
                ", gender=" + gender +
                ", occupation='" + occupation + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", physicalExaminationTimes=" + physicalExaminationTimes +
                ", password=" + password +
                ", role=" + role +
                '}';
    }
}
