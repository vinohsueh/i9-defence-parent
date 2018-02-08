package i9.defence.platform.dao;

import i9.defence.platform.model.PageUrl;

import java.util.List;
import java.util.Map;

/** 
 * 创建时间：2018年2月1日 下午11:52:03
 * @author  lby
 * @version  
 * 
 */
public interface PageUrlDao {
    
    List<PageUrl> selectAllFirstPages() throws Exception;

    PageUrl getPageById(Integer id) throws Exception;

    void addPageUrl(PageUrl pageUrl) throws Exception;

    void updatePageUrl(PageUrl pageUrl) throws Exception;

    List<PageUrl> selectAllPages() throws Exception;

    void delPagesByRoleId(Integer roleId) throws Exception;

    void addPageByRoleId(Map<String, Object> map) throws Exception;

    List<PageUrl> getPageByRoleId(Integer roleId) throws Exception;
}
