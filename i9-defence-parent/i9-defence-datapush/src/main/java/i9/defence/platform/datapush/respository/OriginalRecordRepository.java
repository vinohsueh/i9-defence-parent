package i9.defence.platform.datapush.respository;

import i9.defence.platform.datapush.entity.OriginalRecord;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * 原始数据DAO
 * 
 * @author R12
 * @date 2018年10月22日 14:37:57
 */
public interface OriginalRecordRepository extends JpaRepository<OriginalRecord, String> {

    @Query("SELECT originalRecord FROM OriginalRecord originalRecord WHERE originalRecord.submitDate BETWEEN :startDate AND :endDate ORDER BY originalRecord.submitDate ASC")
    List<OriginalRecord> queryOriginalRecordList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
}
