package cn.goldlone.model;

/**
 * 方法执行后返回的信息
 * 
 * @ClassName: ReturnInfo
 * @author: CN 创建时间: 2017年9月29日 下午8:09:13
 */
public class ReturnInfo {
	// 是否成功
	private boolean success;
	// 返回代码
	private int code;
	// 提示信息
	private String info;

	public ReturnInfo() {
		this.success = false;
		this.code = 0;
		this.info = "";
	}

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
