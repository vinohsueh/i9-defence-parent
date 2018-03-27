package i9.defence.platform.dao;

import java.util.List;

import i9.defence.platform.dao.vo.ClientSearchDto;
import i9.defence.platform.model.Client;
import i9.defence.platform.utils.PageBounds;

/**
* @author : JiaCe
* @version ：2018年1月11日下午3:16:06
* 客户dao
*/
public interface ClientDao {

    /*
     * 增加客户
     */
    void add(Client client)throws Exception;
    /*
     * 批量删除
     */
    void deleteBatch(List<Integer> ids) throws Exception;
    /*
     * 更新
     */
    void update(Client client)throws Exception;
    /*
     * 分页查询
     */
    PageBounds<Client> selectByLimitPage(ClientSearchDto clientSearchDto,
            int currectPage, int pageSize) throws Exception;
    /*
     * 根据id查询
     */
    Client getById(int id)throws Exception;
    /*
     * 查询全部负责人
     */
    List<Client> getAllClient()throws Exception;
    /*
     * 获取创建者的客户
     */
    List<Client> selectByCreateId(Integer createId)throws Exception;
}
