package i9.defence.platform.service;

import i9.defence.platform.model.PageUrl;
import i9.defence.platform.utils.BusinessException;

import java.util.List;

/** 
 * 创建时间：2018年2月1日 下午11:52:03
 * @author  lby
 * @version  
 * 
 */
public interface PageUrlService {
    
    List<PageUrl> selectAllFirstPages() throws BusinessException;

    PageUrl getPageById(Integer id) throws BusinessException;

    void savePage(PageUrl pageUrl) throws BusinessException;

    List<PageUrl> selectAllPages() throws BusinessException;

    List<PageUrl> getPageByRoleId(Integer roleId) throws BusinessException;
}
