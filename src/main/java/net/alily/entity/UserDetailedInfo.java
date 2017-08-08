package net.alily.entity;

/**
 * 用户详细信息类 UserDetailedInfo
 * Created by Alan on 5/13/17.
 */
public class UserDetailedInfo {
    private int id;
    private String name;
    private int age;
    private int sex;
    private String email;
    private String phone;
    //用户地址，用于送货，可以有多个地址，由:分割
    private String address;

    public UserDetailedInfo() {}

    public UserDetailedInfo(String name, int age, int sex, String email, String address) {
        setName(name);
        setAge(age);
        setSex(sex);
        setEmail(email);
        setAddress(address);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSex() {
        return sex;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age > 150 || age <= 0) {
            this.age = 18;
        } else {
            this.age = age;
        }
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setSex(int sex) {
        if(sex != 0 && sex != 1) {
            this.sex = 0;
        } else {
            this.sex = sex;
        }

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCHSex() {
        if(this.sex == 0) {
            return "女";
        } else {
            return "男";
        }
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " age: " + age + " sex: " + sex + " phone: " + phone + " email: " + email + " address: " + address;
    }
}
