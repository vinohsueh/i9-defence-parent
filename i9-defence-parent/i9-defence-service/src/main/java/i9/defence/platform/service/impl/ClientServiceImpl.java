package i9.defence.platform.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ClientDao;
import i9.defence.platform.dao.vo.ClientSearchDto;
import i9.defence.platform.model.Client;
import i9.defence.platform.service.ClientService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.PageBounds;

/**
* @author : JiaCe
* @version ：2018年1月11日下午3:31:10
* 客户service实现类
*/
@Service
@Transactional
public class ClientServiceImpl implements ClientService{

    @Autowired
    private ClientDao clientDao;
    @Override
    public void add(Client client) throws BusinessException {
       try{
           clientDao.add(client);
       }catch (Exception e) {
           throw new BusinessException("添加客户失败",e.getMessage());
       }
    }

    @Override
    public void deleteBatch(List<Integer> ids) throws BusinessException {
        try{
            clientDao.deleteBatch(ids);
        }catch (Exception e) {
            throw new BusinessException("删除失败",e.getMessage());
        }
    }

    @Override
    public void update(Client client) throws BusinessException {
        try{
            clientDao.update(client);
        }catch (Exception e) {
            throw new BusinessException("更新失败",e.getMessage());
        }
    }

    @Override
    public PageBounds<Client> selectByLimitPage(ClientSearchDto clientSearchDto)throws BusinessException {
        try {
            return clientDao.selectByLimitPage(clientSearchDto,clientSearchDto.getCurrentPage(),clientSearchDto.getPageSize());
        } catch (Exception e) {
            throw new BusinessException("分页查询失败",e.getMessage());
        }
    }

    @Override
    public void updateAndAdd(Client client) throws BusinessException {
    	if(client.getCreateId() == null) {
    		 throw new BusinessException("未获取当前登录者的信息!");
    	}
      try{
          if(client.getId() == null){
              //id为空，新建
              client.setCreateTime(new Date());
              clientDao.add(client);
          }else{
              //id不为空,更新
              clientDao.update(client);
          }
      }catch (Exception e) {
          throw new BusinessException("操作失败",e.getMessage());
      }
    }

    @Override
    public Client getById(int id) throws BusinessException {
       try{
           Client client = clientDao.getById(id);
           return client;
       }catch (Exception e) {
           throw new BusinessException("查询失败",e.getMessage());
       }
    }

    @Override
    public List<Client> getAllClient() throws BusinessException {
        try{
            List<Client> list = clientDao.getAllClient();
            return list;
        }catch (Exception e) {
            throw new BusinessException("查询全部失败",e.getMessage());
        }
    }

	@Override
	public List<Client> selectByCreateId(Integer createId) throws BusinessException {
		try {
			List<Client> list = clientDao.selectByCreateId(createId);
			return list;
		}catch (Exception e) {
            throw new BusinessException("查询全部失败",e.getMessage());
        }
	}

	@Override
	public List<Client> selectAllClientByProjectId(Integer projectId) throws BusinessException {
		try{
            return clientDao.selectAllClientByProjectId(projectId);
        }catch (Exception e) {
            throw new BusinessException("根据项目ID 查询全部的责任人  生成下拉选框 进行多选失败",e.getMessage());
        }
	}
}
