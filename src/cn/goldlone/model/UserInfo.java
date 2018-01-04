package cn.goldlone.model;

/**
 * 会员信息功能实体类
 * 
 * @ClassName: UserInfo
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
	// 性别
	private String gender;
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
	// 身份权限
	private int power;
	// 身份名
	private String powerName;
	// 是否加分
	private int addScore;
	// 是否过期
	private int expired;

	public UserInfo() {
	}

	public UserInfo(String no, String name, String stuNo, String phone, String email,
					String gender, String discipline, int grade, int classNum,
					String degree, String id, String startTime, String endTime,
					String memberType, int power, String powerName,
					int addScore, int expired) {
		this.no = no;
		this.name = name;
		this.stuNo = stuNo;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.discipline = discipline;
		this.grade = grade;
		this.classNum = classNum;
		this.degree = degree;
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.memberType = memberType;
		this.power = power;
		this.powerName = powerName;
		this.addScore = addScore;
		this.expired = expired;
	}

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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public int getAddScore() {
		return addScore;
	}

	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}

	public int getExpired() {
		return expired;
	}

	public void setExpired(int expired) {
		this.expired = expired;
	}

	@Override
	public String toString() {
		return "UserInfo{" +
				"no='" + no + '\'' +
				", name='" + name + '\'' +
				", stuNo='" + stuNo + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", gender='" + gender + '\'' +
				", discipline='" + discipline + '\'' +
				", grade=" + grade +
				", classNum=" + classNum +
				", degree='" + degree + '\'' +
				", id='" + id + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", memberType='" + memberType + '\'' +
				", power=" + power +
				", powerName='" + powerName + '\'' +
				", addScore=" + addScore +
				", expired=" + expired +
				'}';
	}
}
