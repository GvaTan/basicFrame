package top.anets.service;

import java.util.List;

import top.anets.entity.Log;
import top.anets.vo.Page;


public interface LogService {
  
    Page getLogs(Integer current,Integer limit);

	/**
	* 
	*@param log 
	*/
	void saveLog(Log log);
}
