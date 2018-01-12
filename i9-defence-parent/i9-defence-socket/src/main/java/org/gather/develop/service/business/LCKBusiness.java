package org.gather.develop.service.business;

import java.util.Date;
import java.util.List;

import org.gather.develop.dataaccess.dao.ConfigInformationDao;
import org.gather.develop.dataaccess.dao.LCKDao;
import org.gather.develop.dataaccess.model.ConfigInformationModel;
import org.gather.develop.dataaccess.model.LCKModel;
import org.gather.develop.service.business.def.IRecordBusiness;
import org.gather.develop.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("LCK")
public class LCKBusiness implements IRecordBusiness {

    @Override
    public String[] execute(List<String> data) throws Exception {
        String msgId = data.get(0);
        String lockId = data.get(1);
        String bikeId = data.get(2);
        String occurTime = data.get(3);
        String hardver = data.get(5);
        String softver = data.get(6);
        String configVer = data.get(7);
        String mac = data.get(11);
        try {
            int lockStatus = Integer.parseInt(data.get(4));
            double bat = Double.parseDouble(data.get(8));
            String gpsN = data.get(9);
            String gpsE = data.get(10);
            LCKModel lckModel = new LCKModel(msgId, lockId, bikeId, DateFormatUtil.parseDate(occurTime), 
                    lockStatus, hardver, softver, configVer, bat, gpsN, gpsE, mac);
            
            lckDao.save(lckModel);
            
            ConfigInformationModel configInformationModel = configInformationDao.getFindConfigInformation(lockId);
            if (configInformationModel != null) {
                configVer = configInformationModel.getConfigVer();
            }
            
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "OK", hardver, softver, configVer, "0"};
        }
        catch (Exception exception) {
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "ERR", "0"};
        }
    }
    
    @Autowired
    private LCKDao lckDao;
    
    @Autowired
    private ConfigInformationDao configInformationDao;
}
