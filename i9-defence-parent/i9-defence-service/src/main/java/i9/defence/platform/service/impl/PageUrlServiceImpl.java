package i9.defence.platform.service.impl;

import i9.defence.platform.dao.PageUrlDao;
import i9.defence.platform.model.PageUrl;
import i9.defence.platform.service.PageUrlService;
import i9.defence.platform.utils.BusinessException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/** 
 * 创建时间：2018年2月2日 上午8:38:08
 * @author  lby
 * @version  
 * 
 */
@Service
@Transactional
public class PageUrlServiceImpl implements PageUrlService{

    @Autowired
    private PageUrlDao pageUrlDao;
    
    @Override
    public List<PageUrl> selectAllFirstPages() throws BusinessException {
        try {
            return pageUrlDao.selectAllFirstPages();
        } catch (Exception e) {
            throw new BusinessException("查询页签失败",e.getMessage());
        }
    }

    @Override
    public PageUrl getPageById(Integer id) throws BusinessException {
        try {
            return pageUrlDao.getPageById(id);
        } catch (Exception e) {
            throw new BusinessException("查询页签失败",e.getMessage());
        }
    }

    @Override
    public void savePage(PageUrl pageUrl) throws BusinessException {
        try {
            if (pageUrl.getId() == null) {
                pageUrlDao.addPageUrl(pageUrl);
            }else{
                pageUrlDao.updatePageUrl(pageUrl);
            }
        } catch (Exception e) {
            throw new BusinessException("保存页签失败",e.getMessage());
        }
        
    }

    @Override
    public List<PageUrl> selectAllPages() throws BusinessException {
        try {
            return pageUrlDao.selectAllPages();
        } catch (Exception e) {
            throw new BusinessException("查询页签失败",e.getMessage());
        }
    }

    @Override
    public List<PageUrl> getPageByRoleId(Integer roleId)
            throws BusinessException {
        try {
            return pageUrlDao.getPageByRoleId(roleId);
        } catch (Exception e) {
            throw new BusinessException("查询页签失败",e.getMessage());
        }
    }

}
