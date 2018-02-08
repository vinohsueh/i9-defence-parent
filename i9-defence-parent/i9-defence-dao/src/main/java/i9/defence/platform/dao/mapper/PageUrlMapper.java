package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.PageUrl;
import i9.defence.platform.model.PageUrlExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface PageUrlMapper {
    int countByExample(PageUrlExample example);

    int deleteByExample(PageUrlExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PageUrl record);

    int insertSelective(PageUrl record);

    List<PageUrl> selectByExample(PageUrlExample example);

    PageUrl selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PageUrl record, @Param("example") PageUrlExample example);

    int updateByExample(@Param("record") PageUrl record, @Param("example") PageUrlExample example);

    int updateByPrimaryKeySelective(PageUrl record);

    int updateByPrimaryKey(PageUrl record);

    List<PageUrl> selectAllFirstPages();

    void delPagesByRoleId(Integer roleId);

    void addPageByRoleId(Map<String, Object> map);

    List<PageUrl> getPageByRoleId(Integer roleId);
}