package cn.goldlone.model;

/**
 * 会员信息功能实体类
 * 
 * @ClassName: UserInfo
 * @Description: TODO
 * @author: CN 创建时间: 2017年10月10日 下午9:26:31
 */
public class UserInfo {
	// 会员号
	private String no;
	// 姓名
	private String name;
	// 学号
	private String stuNo;
	// 手机
	private String phone;
	// 邮箱
	private String email;
	// 专业
	private String discipline;
	// 年级
	private int grade;
	// 班级
	private int classNum;
	// 学历
	private String degree;
	// 身份证号
	private String id;
	// 生效日期
	private String startTime;
	// 失效日期
	private String endTime;
	// 会员类型
	private String memberType;
	// 登录密码
	private String passwd;
	// 身份权限
	private int power;
	// 是否加分
	private int addScore;
	// 是否过期
	private boolean expired;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuNo() {
		return stuNo;
	}

	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
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

	public String getDiscipline() {
		return discipline;
	}

	public void setDiscipline(String discipline) {
		this.discipline = discipline;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getClassNum() {
		return classNum;
	}

	public void setClassNum(int classNum) {
		this.classNum = classNum;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getMemberType() {
		return memberType;
	}

	public void setMemberType(String memberType) {
		this.memberType = memberType;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getAddScore() {
		return addScore;
	}

	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}

	public boolean isExpired() {
		return expired;
	}

	public void setExpired(boolean expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "UserInfo [no=" + no + ", name=" + name + ", stuNo=" + stuNo + ", phone=" + phone + ", email=" + email
				+ ", discipline=" + discipline + ", grade=" + grade + ", classNum=" + classNum + ", degree=" + degree
				+ ", id=" + id + ", startTime=" + startTime + ", endTime=" + endTime + ", memberType=" + memberType
				+ ", passwd=" + passwd + ", power=" + power + ", addScore=" + addScore + ", getNo()=" + getNo()
				+ ", getName()=" + getName() + ", getStuNo()=" + getStuNo() + ", getPhone()=" + getPhone()
				+ ", getEmail()=" + getEmail() + ", getDiscipline()=" + getDiscipline() + ", getGrade()=" + getGrade()
				+ ", getClassNum()=" + getClassNum() + ", getDegree()=" + getDegree() + ", getId()=" + getId()
				+ ", getStartTime()=" + getStartTime() + ", getEndTime()=" + getEndTime() + ", getMemberType()="
				+ getMemberType() + ", getPasswd()=" + getPasswd() + ", getPower()=" + getPower() + ", getAddScore()="
				+ getAddScore() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

}
