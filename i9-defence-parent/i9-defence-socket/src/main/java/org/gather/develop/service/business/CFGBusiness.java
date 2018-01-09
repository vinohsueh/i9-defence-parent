package org.gather.develop.service.business;

import java.util.Date;
import java.util.List;

import org.gather.develop.dataaccess.dao.CFGDao;
import org.gather.develop.dataaccess.dao.ConfigInformationDao;
import org.gather.develop.dataaccess.model.CFGModel;
import org.gather.develop.dataaccess.model.ConfigInformationModel;
import org.gather.develop.service.business.def.IRecordBusiness;
import org.gather.develop.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("CFG")
public class CFGBusiness implements IRecordBusiness {

    @Override
    public String[] execute(List<String> data) throws Exception {
        String msgId = data.get(0);
        String lockId = data.get(1);
        String bikeId = data.get(2);
        String occurTime = data.get(3);
        String mac = data.get(4);
        try {
            CFGModel cfgModel = new CFGModel(msgId, lockId, bikeId, DateFormatUtil.parseDate(occurTime), mac);
            cfgDao.save(cfgModel);
            ConfigInformationModel configInformationModel = configInformationDao.getFindConfigInformation(lockId);
            if (configInformationModel == null) {
                return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "OK", "1.0.1", "103.248.102.28", "8000", bikeId, "0"};
            }
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "OK", 
                configInformationModel.getConfigVer(), configInformationModel.getServerAddress(), configInformationModel.getServerPort(), configInformationModel.getBikeId(), "0"};
        }
        catch (Exception exception) {
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "ERR", "0"};
        }
    }
    
    @Autowired
    private ConfigInformationDao configInformationDao;
    
    @Autowired
    private CFGDao cfgDao;
}
