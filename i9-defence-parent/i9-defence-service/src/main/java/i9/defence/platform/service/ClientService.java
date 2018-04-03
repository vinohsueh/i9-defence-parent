package i9.defence.platform.service;

import java.util.List;

import i9.defence.platform.dao.vo.ClientSearchDto;
import i9.defence.platform.model.Client;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
* @author : JiaCe
* @version ：2018年1月11日下午3:30:43
* 库户service
*/
public interface ClientService {

    /*
     * 增加客户
     */
    void add(Client client)throws BusinessException;
    /*
     * 批量删除
     */
    void deleteBatch(List<Integer> ids) throws BusinessException;
    /*
     * 更新
     */
    void update(Client client)throws BusinessException;
    /*
     * 分页查询
     */
    PageBounds<Client> selectByLimitPage(ClientSearchDto clientSearchDto) throws BusinessException;
    /*
     * 更新/添加客户
     */
    void updateAndAdd(Client client)throws BusinessException;
    /*
     * 根据id查询
     */
    Client getById(int id)throws BusinessException;
    /*
     * 查询客户
     */
    List<Client> getAllClient()throws BusinessException;
    /*
     * 获取创建者的客户
     */
    List<Client> selectByCreateId(Integer createId)throws BusinessException;
    
    //根据项目ID 查询全部的责任人  生成下拉选框 进行多选(此方法 暂时没用了  改为了根据createId)
    List<Client> selectAllClientByProjectId(Integer projectId)throws BusinessException;
}
