package i9.defence.platform.dao.impl;

import i9.defence.platform.dao.PageUrlDao;
import i9.defence.platform.dao.mapper.PageUrlMapper;
import i9.defence.platform.model.PageUrl;
import i9.defence.platform.model.PageUrlExample;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/** 
 * 创建时间：2018年2月1日 下午11:53:55
 * @author  lby
 * @version  
 * 
 */
@Repository
public class PageUrlDaoImpl implements PageUrlDao{
    
    @Autowired
    private PageUrlMapper pageUrlMapper;
    
    @Override
    public List<PageUrl> selectAllFirstPages() throws Exception {
        return pageUrlMapper.selectAllFirstPages();
    }

    @Override
    public PageUrl getPageById(Integer id) throws Exception {
        return pageUrlMapper.selectByPrimaryKey(id);
    }

    @Override
    public void addPageUrl(PageUrl pageUrl) throws Exception {
        pageUrlMapper.insertSelective(pageUrl);
    }

    @Override
    public void updatePageUrl(PageUrl pageUrl) throws Exception {
        pageUrlMapper.updateByPrimaryKeySelective(pageUrl);
    }

    @Override
    public List<PageUrl> selectAllPages() throws Exception {
        PageUrlExample example = new PageUrlExample();
        return pageUrlMapper.selectByExample(example);
    }

    @Override
    public void delPagesByRoleId(Integer roleId) throws Exception {
        pageUrlMapper.delPagesByRoleId(roleId);
    }

    @Override
    public void addPageByRoleId(Map<String, Object> map) throws Exception {
        pageUrlMapper.addPageByRoleId(map);
    }

    @Override
    public List<PageUrl> getPageByRoleId(Integer roleId) throws Exception {
        return pageUrlMapper.getPageByRoleId(roleId);
    }

}
