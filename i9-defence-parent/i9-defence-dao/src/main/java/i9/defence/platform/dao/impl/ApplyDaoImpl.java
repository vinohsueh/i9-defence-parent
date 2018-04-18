package i9.defence.platform.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.dao.mapper.ApplyMapper;
import i9.defence.platform.dao.vo.ApplyDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ApplyExample;
import i9.defence.platform.model.ApplyExample.Criteria;
import i9.defence.platform.utils.PageBounds;

/**
 * @ClassName: ApplyDaoImpl
 * @Description: TODO
 * @author: luobo
 * @date: 2018年1月25日 上午10:09:12
 */
@Repository
public class ApplyDaoImpl implements ApplyDao {

	@Autowired
	private ApplyMapper applyMapper;

	@Override
	public PageBounds<Apply> selectByLimitPage(ApplyExample applyExample, int currectPage, int pageSize)
			throws Exception {
		final int totalSize = applyMapper.countByExample(applyExample);
		PageBounds<Apply> pageBounds = new PageBounds<Apply>(currectPage, totalSize, pageSize);
		List<Apply> list = applyMapper.selectByLimitPage(applyExample, pageBounds.getOffset(),
				pageBounds.getPageSize());
		pageBounds.setPageList(list);
		return pageBounds;
	}

	/**
	 * 删除申请
	 */
	@Override
	public void delApply(List<Integer> ids) throws Exception {
		applyMapper.deleteByPrimaryKey(ids);
	}

	/**
	 * 批量插入
	 */
	@Override
	public void insertEquipmentApplys(List<Apply> applies) throws Exception {
		applyMapper.insertEquipmentApplys(applies);
	}

	/**
	 * 批量更新
	 */
	@Override
	public void updateApplys(List<Apply> applies) throws Exception {
		applyMapper.updateApplys(applies);
	}

	@Override
	public Apply getApplyById(int id) throws Exception {
		return applyMapper.selectByPrimaryKey(id);
	}

	@Override
	public void insertProjectApplys(List<Apply> applies) throws Exception {
		applyMapper.insertProjectApplys(applies);
	}

	/**
	 * 查询部分申请
	 */
	@Override
	public List<Apply> selectPartState(Integer state) throws Exception {
		ApplyExample example = new ApplyExample();
		Criteria criteria = example.createCriteria();
		criteria.andStateEqualTo(state);
		List<Apply> list = applyMapper.selectByExample(example);
		return list;
	}

	@Override
	public int selectProjectCount(List<Integer> ids) throws Exception {
		int count = applyMapper.selectProjectCount(ids);
		return count;
	}

	@Override
	public int selectEquipmentCount(List<Integer> ids) throws Exception {
		int count = applyMapper.selectEquipmentCount(ids);
		return count;
	}

	@Override
	public List<Apply> selectApplyByids(List<Integer> ids) throws Exception {
		ApplyExample example = new ApplyExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdIn(ids);
		List<Apply> list = applyMapper.selectByExample(example);
		return list;
	}

	@Override
	public PageBounds<Apply> selectByLimitPage2(ApplyExample applyExample, int currectPage, int pageSize,
			Integer destriId) throws Exception {
		final int totalSize = applyMapper.countByExample2(applyExample);
		ArrayList<Apply> Onedestris = new ArrayList<>();
		ArrayList<Apply> Twodestris = new ArrayList<>();
		PageBounds<Apply> pageBounds = new PageBounds<Apply>(currectPage, totalSize, pageSize);
		List<ApplyDto> list = applyMapper.selectByLimitPage2(applyExample, pageBounds.getOffset(),
				pageBounds.getPageSize());
		if (destriId != null && 1 == destriId) {
			for (ApplyDto applyDto : list) {
				if (null == applyDto.getConductParentId()) {
					Apply apply = new Apply();
					apply.setId(applyDto.getId());
					apply.setType(applyDto.getType());
					apply.setState(applyDto.getState());
					apply.setApplyDate(applyDto.getApplyDate());
					apply.setApplyId(applyDto.getApplyId());
					apply.setApplyManager(applyDto.getApplyManager());
					apply.setEquipmentId(applyDto.getEquipmentId());
					apply.setEquipment(applyDto.getEquipment());
					apply.setProjectId(applyDto.getProjectId());
					apply.setProject(applyDto.getProject());
					Onedestris.add(apply);
				}
			}
			pageBounds.setPageList(Onedestris);
			return pageBounds;
		} else if (destriId != null && 2 == destriId) {
			for (ApplyDto applyDto : list) {
				if (null != applyDto.getConductParentId()) {
					Apply apply = new Apply();
					apply.setId(applyDto.getId());
					apply.setType(applyDto.getType());
					apply.setState(applyDto.getState());
					apply.setApplyDate(applyDto.getApplyDate());
					apply.setApplyId(applyDto.getApplyId());
					apply.setApplyManager(applyDto.getApplyManager());
					apply.setEquipmentId(applyDto.getEquipmentId());
					apply.setEquipment(applyDto.getEquipment());
					apply.setProjectId(applyDto.getProjectId());
					apply.setProject(applyDto.getProject());
					Twodestris.add(apply);
				}
			}
			pageBounds.setPageList(Twodestris);
			return pageBounds;
		}
		return null;

	}
}
