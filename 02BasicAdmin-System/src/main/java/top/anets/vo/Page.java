package top.anets.vo;

import java.io.Serializable;

import lombok.Data;

@Data
public class Page implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = -2229935370100678744L;
	
	private Object data;
	//每页显示条数
	private Integer limit;
    //数据总数
	private Long count;
    //当前页
	private Integer curr;
}
