package i9.defence.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.UpStreamDecodeDao;
import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
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
    public void saveUpStreamDecode(String jsonStr) {
        UpStreamDecode upStreamDecode = new UpStreamDecode();
        upStreamDecode.setHexStr(jsonStr);
        upStreamDecode.setSubmitDate(new Date());
        this.addUpStreamDecode(upStreamDecode);
    }

}
