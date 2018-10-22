package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.OriginalRecord;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 原始数据DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:37:57
 */
public interface OriginalRecordRepository extends JpaRepository<OriginalRecord, String> {
}
