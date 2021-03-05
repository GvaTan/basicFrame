package com.cy.pj.sys.entity;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
/**
 * 日志持久化对象:(PO-Persistant Object)
 * 建议:java中所有用于封装数据的对象都实现序列化接口
 * 便于后续进行扩展.
 */
@Data
public class SysLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8924387722922123121L;
	/**
	 * 序列化id,对象序列化时的唯一标识,不定义此id
	 * 对象序列化时,也会自动生成一个id(这个id的生成
	 * 是基于类的结构信息自动生成),它会与对象对应
	 * 的字节存储在一起.但是假如类的结构在反序列化
	 * 时已经发生变化,可能保证原有字节能够正常反序
	 * 列化.
	 */
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
