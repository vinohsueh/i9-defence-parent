package i9.defence.platform.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.StreamOriginDao;
import i9.defence.platform.dao.vo.StreamOriginSearchDto;
import i9.defence.platform.model.StreamOrigin;
import i9.defence.platform.service.UpStreamOriginService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

@Service
@Transactional
public class UpStreamOriginServiceImpl implements UpStreamOriginService {

    @Autowired
    private StreamOriginDao streamOriginDao;

    @Override
    public PageBounds<StreamOrigin> selectByLimitPage(StreamOriginSearchDto streamOriginSearchDto)
            throws BusinessException {
        try {
            return streamOriginDao.selectByLimitPage(streamOriginSearchDto, streamOriginSearchDto.getCurrentPage(),
                    streamOriginSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页项目类别类别查询失败", e.getMessage());
        }
    }

    @Override
    public void addStreamOrigin(StreamOrigin streamOrigin) throws BusinessException {
        try {
            streamOriginDao.addStreamOrigin(streamOrigin);
        } catch (Exception e) {
            throw new BusinessException("添加原始数据失败", e.getMessage());
        }
    }

    @Override
    public void saveUpStreamOrigin(String str) {
        StreamOrigin streamOrigin = new StreamOrigin();
        streamOrigin.setJsonstr(str);
        streamOrigin.setSubmitDate(new Date());
        this.addStreamOrigin(streamOrigin);
    }
}
