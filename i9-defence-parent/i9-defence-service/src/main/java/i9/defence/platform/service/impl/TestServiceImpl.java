package i9.defence.platform.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.TestDao;
import i9.defence.platform.dao.vo.Demo;
import i9.defence.platform.service.TestService;
import i9.defence.platform.utils.TargetDataSource;

/** 
* 创建时间：2018年5月7日 下午1:55:48
* @author  lby
* @version  
* 
*/
@Service
@Transactional
public class TestServiceImpl implements TestService{
	
	@Autowired
	private TestDao testDao;
	
	@TargetDataSource("ds1")
	@Override
	public List<Demo> getListByDs1() {
		return testDao.getList();
	}

}
