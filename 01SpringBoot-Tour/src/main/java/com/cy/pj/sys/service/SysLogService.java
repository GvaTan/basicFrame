package com.cy.pj.sys.service;

import com.cy.pj.common.vo.PageObject;
import com.cy.pj.sys.entity.SysLog;

public interface SysLogService {

	int deleteObjects(Integer... ids);
	
	PageObject<SysLog> findPageObjects(String username,
			Integer pageCurrent);
	
	/**
	 * 记录日志
	 * @param entity
	 */
	void saveObject(SysLog entity);
}
