package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.UpStreamDecodeDao;
import i9.defence.platform.dao.mapper.UpStreamDecodeMapper;
import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月19日 上午11:07:17 
* 
*/
@Repository
public class UpStreamDecodeDaoImpl implements UpStreamDecodeDao{
	
	@Autowired
	private UpStreamDecodeMapper upStreamDecodeMapper;

	@Override
	public PageBounds<UpStreamDecode> selectByLimitPage(UpStreamDecodeSearchDto upStreamDecodeSearchDto,
			int currectPage, int pageSize) throws Exception {
        final int totalSize = (int) upStreamDecodeMapper.countByExample(upStreamDecodeSearchDto);
        PageBounds<UpStreamDecode> pageBounds = new PageBounds<UpStreamDecode>(currectPage, totalSize, pageSize);
        List<UpStreamDecode> list = upStreamDecodeMapper.selectByLimitPage(upStreamDecodeSearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

	@Override
	public void addUpStreamDecode(UpStreamDecode upStreamDecode) throws Exception {
		upStreamDecodeMapper.insertSelective(upStreamDecode);
	}

	@Override
	public void deleteUpStreamDecode(List<Integer> ids) throws Exception {
		upStreamDecodeMapper.deleteByPrimaryKey(ids);
	}

}
 