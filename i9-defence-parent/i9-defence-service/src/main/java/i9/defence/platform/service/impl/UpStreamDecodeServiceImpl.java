package i9.defence.platform.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import i9.defence.platform.dao.ChannelDataDao;
import i9.defence.platform.dao.UpStreamDecodeDao;
import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.service.UpStreamDecodeService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
 * @author user: jiace
 * @version creatTime：2018年3月19日 上午11:14:14
 * 
 */
@Service
@Transactional
public class UpStreamDecodeServiceImpl implements UpStreamDecodeService {

    @Autowired
    private UpStreamDecodeDao upStreamDecodeDao;
    
    @Autowired
    private ChannelDataDao channelDataDao;
    @Override
    public void addUpStreamDecode(UpStreamDecode upStreamDecode) throws BusinessException {
        try {
            upStreamDecode.setSubmitDate(new Date());
            upStreamDecodeDao.addUpStreamDecode(upStreamDecode);
        } catch (Exception e) {
            throw new BusinessException("添加解密数据失败", e.getMessage());
        }
    }

    @Override
    public PageBounds<UpStreamDecode> selectByLimitPage(UpStreamDecodeSearchDto upStreamDecodeSearchDto)
            throws BusinessException {
        try {
            return upStreamDecodeDao.selectByLimitPage(upStreamDecodeSearchDto,
                    upStreamDecodeSearchDto.getCurrentPage(), upStreamDecodeSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页查询解密数据失败", e.getMessage());
        }
    }

    @Override
    public void deleteUpStreamDecode(List<Integer> ids) throws BusinessException {
        try {
            upStreamDecodeDao.deleteUpStreamDecode(ids);
        } catch (Exception e) {
            throw new BusinessException("删除解密数据失败", e.getMessage());
        }
    }

    @Override
    public void saveUpStreamDecode(String jsonStr) throws Exception {
    	//添加解析数据
        UpStreamDecode upStreamDecode = new UpStreamDecode();
        upStreamDecode.setHexStr(jsonStr);
        upStreamDecode.setSubmitDate(new Date());
        
        //将数据添加到通道数据表中
        
        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
		//获取通道数据
		List<ChannelData> list = new ArrayList<ChannelData>();
		JSONArray object = (JSONArray) jsonObject.get("dataList");
		for (Object object2 : object) {
			ChannelData channelData = new ChannelData();
			channelData.setSystemId((String)jsonObject.get("systemId"));
			JSONObject jsonObject2 = (JSONObject)object2;
			channelData.setType((int)jsonObject2.get("type"));
			channelData.setChannel((int)jsonObject2.get("channel"));
			channelData.setValue(String.valueOf(jsonObject2.get("value")));
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			channelData.setDateTime(simpleDateFormat.parse((String)(jsonObject2.get("datetime").toString().replace("#", " "))));
			list.add(channelData);
		}
		try {
			this.addUpStreamDecode(upStreamDecode);
			channelDataDao.insertBatch(list);
		} catch (Exception e) {
			throw new BusinessException(e.getMessage());
		}
    }
}
