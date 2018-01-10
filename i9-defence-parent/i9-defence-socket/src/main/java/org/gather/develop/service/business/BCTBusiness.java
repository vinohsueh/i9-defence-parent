package org.gather.develop.service.business;

import java.util.Date;
import java.util.List;

import org.gather.develop.dataaccess.dao.BCTDao;
import org.gather.develop.dataaccess.dao.ConfigInformationDao;
import org.gather.develop.dataaccess.dao.Lock0Dao;
import org.gather.develop.dataaccess.model.BCTModel;
import org.gather.develop.dataaccess.model.ConfigInformationModel;
import org.gather.develop.service.business.def.IRecordBusiness;
import org.gather.develop.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("BCT")
public class BCTBusiness implements IRecordBusiness {

    @Override
    public String[] execute(List<String> data) throws Exception {
        String msgId = data.get(0);
        String lockId = data.get(1);
        String bikeId = data.get(2);
        String mac = data.get(12);
        try {
            String lendBikeTime = data.get(3);
            String tradeType = data.get(4);
            String lockStatus = data.get(5);
            String hardver = data.get(6);
            String softver = data.get(7);
            String configver = data.get(8);
            double bat = Double.parseDouble(data.get(9));
            String gpsN = data.get(10);
            String gpsE = data.get(11);
            Date lendBikeTime0 = null;
            if (lendBikeTime.equals("") == false) {
                lendBikeTime0 = DateFormatUtil.parseDate(lendBikeTime);
            }
            BCTModel bctModel = new BCTModel(msgId, lockId, bikeId, 
            		lendBikeTime0, Integer.parseInt(tradeType), Integer.parseInt(lockStatus), hardver, softver, configver, bat, gpsN, gpsE, mac);

            bctDao.save(bctModel);
            
            ConfigInformationModel configInformationModel = configInformationDao.getFindConfigInformation(lockId);
            if (configInformationModel != null) {
                configver = configInformationModel.getConfigVer();
            }
            
            lock0Dao.update(bctModel);
            
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "OK", hardver, softver, configver, "0"};
        }
        catch (Exception exception) {
        	exception.printStackTrace();
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "ERR", "0"};
        }

    }
    
    @Autowired
    private ConfigInformationDao configInformationDao;
    
    @Autowired
    private BCTDao bctDao;
    
    @Autowired
    private Lock0Dao lock0Dao;
}
