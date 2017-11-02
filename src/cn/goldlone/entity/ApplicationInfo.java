package cn.goldlone.entity;

/**
 * 报名信息
 * Created by CN on 2017/10/31.
 */
public class ApplicationInfo {
    private String certNo;
    private String name;
    private String memberNo;
    private String id;
    private String phone;
    private String email;
    private String language;
    private String degree;
    private String purpose;
    private String school;
    private String username;
    private String password;
    private String photoName;

    public String getCertNo() {
        return certNo;
    }

    public void setCertNo(String certNo) {
        this.certNo = certNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    @Override
    public String toString() {
        return "ApplicationInfo{" +
                "certNo='" + certNo + '\'' +
                ", name='" + name + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", degree='" + degree + '\'' +
                ", purpose='" + purpose + '\'' +
                ", school='" + school + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photoName='" + photoName + '\'' +
                '}';
    }
}
