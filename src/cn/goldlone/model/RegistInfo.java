package cn.goldlone.model;

/**
 * 添加新会员信息时返回的信息
 * 
 * @ClassName: RegistInfo
 * @Description: TODO
 * @author: CN 创建时间: 2017年9月29日 下午8:09:13
 */
public class RegistInfo {
	// 是否成功
	private boolean success;
	// 错误代码
	private int code;
	// 提示信息
	private String info;

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

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
