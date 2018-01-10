package cn.goldlone.po;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * 会员信息表映射
 * 
 * @ClassName: Member
 * @author: CN 创建时间: 2017年9月28日 下午4:19:46
 */
public class Member {
	// 会员号
	private String no;
	// 姓名
	private String name;
	// 学号
	private String stuNo;
	// 电话
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
	// 学历编号
	private int degreeNo;
	// 身份证号
	private String id;
	// 生效日期
	private String startTime;
	// 失效日期
	private String endTime;
	// 会员类型编号
	private int memberTypeNo;
	// 照片所在地址
	private String photo;
	// 登录密码
	private String password;
	// 身份权限
	private int power;
	// 是否加分
	private int addScore;

	public Member() {
	}

	public Member(String no, String name, String stuNo, String phone, String email,
				  String gender, String discipline, int grade, int classNum, int degreeNo,
				  String id, String startTime, String endTime, int memberTypeNo, String photo,
				  String password, int power, int addScore) {
		this.no = no;
		this.name = name;
		this.stuNo = stuNo;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.discipline = discipline;
		this.grade = grade;
		this.classNum = classNum;
		this.degreeNo = degreeNo;
		this.id = id;
		this.startTime = startTime;
		this.endTime = endTime;
		this.memberTypeNo = memberTypeNo;
		this.photo = photo;
		this.password = password;
		this.power = power;
		this.addScore = addScore;
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

	public int getDegreeNo() {
		return degreeNo;
	}

	public void setDegreeNo(int degreeNo) {
		this.degreeNo = degreeNo;
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

	public int getMemberTypeNo() {
		return memberTypeNo;
	}

	public void setMemberTypeNo(int memberTypeNo) {
		this.memberTypeNo = memberTypeNo;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
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

	public int getAddScore() {
		return addScore;
	}

	public void setAddScore(int addScore) {
		this.addScore = addScore;
	}

	@Override
	public String toString() {
		return "Member{" +
				"no='" + no + '\'' +
				", name='" + name + '\'' +
				", stuNo='" + stuNo + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", gender='" + gender + '\'' +
				", discipline='" + discipline + '\'' +
				", grade=" + grade +
				", classNum=" + classNum +
				", degreeNo=" + degreeNo +
				", id='" + id + '\'' +
				", startTime='" + startTime + '\'' +
				", endTime='" + endTime + '\'' +
				", memberTypeNo=" + memberTypeNo +
				", photo='" + photo + '\'' +
				", password='" + password + '\'' +
				", power=" + power +
				", addScore=" + addScore +
				'}';
	}
}
