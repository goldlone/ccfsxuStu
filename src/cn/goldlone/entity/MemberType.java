package cn.goldlone.entity;

/**
 * 会员类型表映射
 * 
 * @ClassName: MemberType
 * @Description: TODO
 * @author: CN 创建时间: 2017年9月29日 下午6:55:21
 */
public class MemberType {
	// 会员类型编号
	private int typeNo;
	// 会员类型
	private String typeName;

	public int getTypeNo() {
		return typeNo;
	}

	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
