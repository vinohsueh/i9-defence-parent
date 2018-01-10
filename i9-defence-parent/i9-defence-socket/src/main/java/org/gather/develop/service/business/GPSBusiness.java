package org.gather.develop.service.business;

import java.util.Date;
import java.util.List;

import org.gather.develop.dataaccess.dao.GPSDao;
import org.gather.develop.dataaccess.model.GPSModel;
import org.gather.develop.service.business.def.IRecordBusiness;
import org.gather.develop.service.util.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("GPS")
public class GPSBusiness implements IRecordBusiness {

	@Override
	public String[] execute(List<String> data) throws Exception {
        String msgId = data.get(0);
        String lockId = data.get(1);
        String bikeId = data.get(2);
        try {
        	String occurTime = data.get(3);
        	double bat = Double.parseDouble(data.get(4));
        	String gpsN = data.get(5);
        	String gpsE = data.get(6);
        	String mac = data.get(7);
        	GPSModel gpsModel = new GPSModel(msgId, lockId, bikeId, DateFormatUtil.parseDate(occurTime), bat, gpsN, gpsE, mac);
        	gpsDao.save(gpsModel);
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "OK", "0"};
        }
        catch (Exception exception) {
        	exception.printStackTrace();
            return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "ERR", "0"};
        }
	}
	
	@Autowired
	private GPSDao gpsDao;

}
