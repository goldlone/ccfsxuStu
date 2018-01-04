package cn.goldlone.model;

/**
 * 登录信息
 * Created by CN on 2017/12/30.
 */
public class LoginInfo {
    // 会员号
    private String memberNo;
    // 密码
    private String password;
    // 权限
    private int power;

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "memberNo='" + memberNo + '\'' +
                ", password='" + password + '\'' +
                ", power=" + power +
                '}';
    }
}
