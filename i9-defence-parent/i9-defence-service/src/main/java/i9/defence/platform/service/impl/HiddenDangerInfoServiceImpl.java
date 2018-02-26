package i9.defence.platform.service.impl;

import i9.defence.platform.dao.HiddenDangerInfoDao;
import i9.defence.platform.model.HiddenDangerInfo;
import i9.defence.platform.model.HiddenDangerInfoExample;
import i9.defence.platform.service.HiddenDangerInfoService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 隐患提醒类型ServiceImpl
 * @ClassName: HiddenDangerInfoServiceImpl 
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月10日 下午4:54:30
 */
@Service
public class HiddenDangerInfoServiceImpl implements HiddenDangerInfoService {

	@Autowired
	private HiddenDangerInfoDao hiddenDangerInfoDao;
	
	@Override
	public void addHiddenDangerInfo(HiddenDangerInfo hiddenDangerInfo)
			throws BusinessException {
		try {
			if(hiddenDangerInfo.getId()!=null){
				
			}else{
				hiddenDangerInfoDao.addHiddenDangerInfo(hiddenDangerInfo);
			}
		} catch (Exception e) {
			throw new BusinessException("添加隐患类型具体信息失败",e.getMessage());
		}
		 
	}

	@Override
	public PageBounds<HiddenDangerInfo> selectByLimitPage(
			HiddenDangerInfoExample hiddenDangerInfoExample,int currectPage, int pageSize) throws BusinessException {
		try {
			return hiddenDangerInfoDao.selectByLimitPage(hiddenDangerInfoExample,currectPage,pageSize);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException("分页查询隐患类型具体信息失败",e.getMessage());
		}
	}
	
	/**
	 * 根据id查询
	 */
	@Override
	public HiddenDangerInfo selectById(int id) throws BusinessException {
		try {
			return hiddenDangerInfoDao.selectById(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new BusinessException("根据id查询查询隐患类型具体信息失败",e.getMessage());
		} 
	}

 

}
