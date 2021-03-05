package top.anets.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class Log implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2802944817202015565L;
	private Integer id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//执行时长(毫秒)
	private Long time;
	//IP地址
	private String ip;
	//创建时间
	private Date createdTime;

}
