package org.gather.develop.service.business;

import java.util.Date;
import java.util.List;

import org.gather.develop.service.business.def.IRecordBusiness;
import org.gather.develop.service.util.DateFormatUtil;
import org.springframework.stereotype.Service;

@Service("OPL")
public class OPLBusiness implements IRecordBusiness {

	@Override
	public String[] execute(List<String> data) throws Exception {
		String msgId = data.get(0);
		String lockId = data.get(1);
		String bikeId = data.get(2);
//		String LendBikeTime = data.get(3);
//		String TRADETYPE = data.get(4);
//		String LockStatus = data.get(5);
		String hardver = data.get(6);
		String softver = data.get(7);
		String configver = data.get(8);
//		String Bat = data.get(9);
//		String gpsN = data.get(10);
//		String gpsE = data.get(11);
//		String mac = data.get(12);
		return new String[] {msgId, lockId, bikeId, DateFormatUtil.formatDate(new Date()), "OK", hardver, softver, configver, "0"};
	}
}
