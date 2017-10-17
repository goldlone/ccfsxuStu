package cn.goldlone.model;

public class ScoreInfo {
	// 认证名称
	private String certName;
	// 姓名
	private String memberName;
	// 会员号
	private String memberNo;
	// 总成绩
	private int all;
	// 第一题
	private int first;
	// 第二题
	private int sencond;
	// 第三题
	private int third;
	// 第四题
	private int forth;
	// 第五题
	private int fifth;

	public String getCertName() {
		return certName;
	}

	public void setCertName(String certName) {
		this.certName = certName;
	}

	public String getMemberName() {
		return memberName;
	}

	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	public String getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}

	public int getAll() {
		return all;
	}

	public void setAll(int all) {
		this.all = all;
	}

	public int getFirst() {
		return first;
	}

	public void setFirst(int first) {
		this.first = first;
	}

	public int getSencond() {
		return sencond;
	}

	public void setSencond(int sencond) {
		this.sencond = sencond;
	}

	public int getThird() {
		return third;
	}

	public void setThird(int third) {
		this.third = third;
	}

	public int getForth() {
		return forth;
	}

	public void setForth(int forth) {
		this.forth = forth;
	}

	public int getFifth() {
		return fifth;
	}

	public void setFifth(int fifth) {
		this.fifth = fifth;
	}

	@Override
	public String toString() {
		return "ScoreInfo [certName=" + certName + ", memberName=" + memberName + ", memberNo=" + memberNo + ", all="
				+ all + ", first=" + first + ", sencond=" + sencond + ", third=" + third + ", forth=" + forth
				+ ", fifth=" + fifth + "]";
	}

}
