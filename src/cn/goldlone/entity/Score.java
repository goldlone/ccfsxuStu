package cn.goldlone.entity;

public class Score {
	// 认证编号
	private int certNo;
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

	public int getCertNo() {
		return certNo;
	}

	public void setCertNo(int certNo) {
		this.certNo = certNo;
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

}
