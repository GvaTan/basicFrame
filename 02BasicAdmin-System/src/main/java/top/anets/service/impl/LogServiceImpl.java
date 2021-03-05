package top.anets.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import top.anets.annotation.RequireLog;
import top.anets.dao.LogDao;
import top.anets.entity.Log;
import top.anets.service.LogService;
import top.anets.vo.Page;

@Service
public class LogServiceImpl implements LogService{
    @Autowired
    private LogDao logDao;
    @RequireLog(operation="查询日志")
	@Override
	public Page getLogs(Integer current, Integer limit) {
		PageHelper.startPage(current,limit);
		List<Log> list = logDao.selectAll();
		
		PageInfo<Log> pageInfo = new PageInfo<>(list);
		Page page = new Page();
		page.setCount(pageInfo.getTotal());
		page.setCurr(current);
		page.setLimit(limit);
		page.setData(list);
		return page;
	}
	@Override
	public void saveLog(Log log) {
		logDao.insert(log);
	}
    
}
