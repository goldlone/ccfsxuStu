package cn.goldlone.model;

/**
 * 登录返回信息
 * Created by CN on 2017/11/3.
 */
public class LoginInfo {
    private boolean success;
    private int code;
    private String memberNo;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
}
