/**
 * 
 */
package top.anets.vo;

import java.io.Serializable;

import lombok.Data;

/**
 * @author Administrator
 *
 */
@Data
public class Result implements Serializable{
	private Boolean success=true;
    private String msg="操作成功";
    private Object data;
	/**
	 * @param success
	 * @param msg
	 * @param data
	 */
    
    public Result() {
    	
    }
	public Result(Boolean success, String msg, Object data) {
		super();
		this.success = success;
		this.msg = msg;
		this.data = data;
	}
	
	public Result(Object data) {
		
		this.data = data;
	}
    
} 
