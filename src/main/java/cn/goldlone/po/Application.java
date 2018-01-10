package cn.goldlone.po;

/**
 * 报名信息
 * Created by CN on 2017/10/31.
 */
public class Application {
    // 认证编号
    private int certNo;
    // 认证名
    private String name;
    // 会员名
    private String memberName;
    // 会员号
    private String memberNo;
    // 性别
    private String gender;
    // 身份证号
    private String id;
    // 电话
    private String phone;
    // 邮箱
    private String email;
    // 编程语言
    private String language;
    // 学历
    private String degree;
    // 年级
    private int grade;
    // 认证目的
    private String purpose;
    // 意向学校
    private String school;
    // 用户名
    private String username;
    // 密码
    private String password;
    // 照片地址
    private String photo;

    public Application() {
    }

    public Application(int certNo, String name, String memberName, String memberNo,
                       String gender, String id, String phone, String email,
                       String language, String degree, int grade, String purpose,
                       String school, String username, String password, String photo) {
        this.certNo = certNo;
        this.name = name;
        this.memberName = memberName;
        this.memberNo = memberNo;
        this.gender = gender;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.language = language;
        this.degree = degree;
        this.grade = grade;
        this.purpose = purpose;
        this.school = school;
        this.username = username;
        this.password = password;
        this.photo = photo;
    }

    public int getCertNo() {
        return certNo;
    }

    public void setCertNo(int certNo) {
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

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Application{" +
                "certNo=" + certNo +
                ", name='" + name + '\'' +
                ", memberName='" + memberName + '\'' +
                ", memberNo='" + memberNo + '\'' +
                ", gender='" + gender + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", language='" + language + '\'' +
                ", degree='" + degree + '\'' +
                ", grade=" + grade +
                ", purpose='" + purpose + '\'' +
                ", school='" + school + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }
}
