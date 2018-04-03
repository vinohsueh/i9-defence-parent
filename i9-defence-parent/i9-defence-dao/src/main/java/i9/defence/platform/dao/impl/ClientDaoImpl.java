package i9.defence.platform.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import i9.defence.platform.dao.ClientDao;
import i9.defence.platform.dao.mapper.ClientMapper;
import i9.defence.platform.dao.vo.ClientSearchDto;
import i9.defence.platform.model.Client;
import i9.defence.platform.model.ClientExample;
import i9.defence.platform.model.ClientExample.Criteria;
import i9.defence.platform.utils.PageBounds;

/**
* @author : JiaCe
* @version ：2018年1月11日下午3:27:02
* 客户dao实现类
*/
@Repository
public class ClientDaoImpl implements ClientDao{

    @Autowired
    private ClientMapper clientMapper;
    @Override
    public void add(Client client) throws Exception {
        clientMapper.insertSelective(client);
    }

    @Override
    public void deleteBatch(List<Integer> ids) throws Exception {
        clientMapper.deleteByPrimaryKey(ids);
    }

    @Override
    public void update(Client client) throws Exception {
        clientMapper.updateByPrimaryKeySelective(client);
    }

    @Override
    public PageBounds<Client> selectByLimitPage(ClientSearchDto clientSearchDto, int currectPage, int pageSize)
            throws Exception {
        final int totalSize = (int) clientMapper.countByExample(clientSearchDto);
        PageBounds<Client> pageBounds = new PageBounds<Client>(currectPage, totalSize, pageSize);
        List<Client> list = clientMapper.selectByLimitPage(clientSearchDto, pageBounds.getOffset(), pageBounds.getPageSize());
        pageBounds.setPageList(list);
        return pageBounds;
    }

    @Override
    public Client getById(int id) throws Exception {
        Client client = clientMapper.selectByPrimaryKey(id);
        return client;
    }

    @Override
    public List<Client> getAllClient() throws Exception {
        List<Client> list = clientMapper.selectAllClient();
        return list;
    }

	@Override
	public List<Client> selectByCreateId(Integer createId) throws Exception {
		List<Client> list = clientMapper.selectByCreateId(createId);
		return list;
	}

	@Override
	public List<Client> selectAllClientByProjectId(Integer projectId) throws Exception {
		ClientExample example = new ClientExample();
		Criteria criteria = example.createCriteria();
		criteria.andProjectIdEqualTo(projectId);
		return clientMapper.selectByExample(example);
	}

}
