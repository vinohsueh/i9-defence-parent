package i9.defence.platform.dao.mapper;

import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.model.ErrHandleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ErrHandleMapper {
    long countByExample(ErrHandleExample example);

    int deleteByExample(ErrHandleExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ErrHandle record);

    int insertSelective(ErrHandle record);

    List<ErrHandle> selectByExample(ErrHandleExample example);

    ErrHandle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ErrHandle record, @Param("example") ErrHandleExample example);

    int updateByExample(@Param("record") ErrHandle record, @Param("example") ErrHandleExample example);

    int updateByPrimaryKeySelective(ErrHandle record);

    int updateByPrimaryKey(ErrHandle record);
}