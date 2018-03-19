package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.UpStreamDecodeSearchDto;
import i9.defence.platform.model.UpStreamDecode;
import i9.defence.platform.utils.PageBounds;

/** 
* @author user: jiace
* @version creatTime：2018年3月19日 上午11:05:49 
* 
*/
public interface UpStreamDecodeDao {
	
	 /**
     * 添加解密数据
     * @param UpStreamDecode
     * @throws Exception
     */
    void addUpStreamDecode(UpStreamDecode upStreamDecode) throws Exception;

	/**
     * 分页查解密数据
     * @param upStreamDecodeSearchDto
     * @param currectPage
     * @param pageSize
     * @return
     */
    PageBounds<UpStreamDecode> selectByLimitPage(UpStreamDecodeSearchDto upStreamDecodeSearchDto,
            int currectPage, int pageSize) throws Exception;
    /**
    * 删除解密数据
    * @param kid
    * @throws Exception
    */
   void deleteUpStreamDecode(List<Integer> ids) throws Exception;
}
 