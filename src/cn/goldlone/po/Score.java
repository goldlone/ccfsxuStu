package cn.goldlone.po;

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
	private int second;
	// 第三题
	private int third;
	// 第四题
	private int forth;
	// 第五题
	private int fifth;

    public Score() {
    }

    public Score(int certNo, String memberNo, int all, int first,
                 int second, int third, int forth, int fifth) {
        this.certNo = certNo;
        this.memberNo = memberNo;
        this.all = all;
        this.first = first;
        this.second = second;
        this.third = third;
        this.forth = forth;
        this.fifth = fifth;
    }

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

	public int getSecond() {
		return second;
	}

	public void setSecond(int second) {
		this.second = second;
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
		return "Score{" +
				"certNo=" + certNo +
				", memberNo='" + memberNo + '\'' +
				", all=" + all +
				", first=" + first +
				", second=" + second +
				", third=" + third +
				", forth=" + forth +
				", fifth=" + fifth +
				'}';
	}
}
