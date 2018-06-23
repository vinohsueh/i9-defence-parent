package i9.defence.platform.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import i9.defence.platform.dao.EquipmentFaultDao;
import i9.defence.platform.model.EquipmentFault;

/** 
* 创建时间：2018年4月14日 上午2:40:30
* @author  lby
* @version  
* 
*/
@Component
public class ErrorTypeCache {
	@Autowired
	private EquipmentFaultDao equipmentFaultDao;
	
	/**
     *  code和name
     *  
     */
    public static Map<String, String> dictMap = new HashMap<String, String>();
    
    /**
     *  code和type
     *  
     */
    public static Map<String, Integer> dictTypeMap = new HashMap<String, Integer>();
    
    /**
     * code和对应的名称类型 (是否多名称)
     */
    public static Map<String, Integer> dictJhTypeMap = new HashMap<String, Integer>();
    
    /**
     * code和对应的 故障名称1对应的通道号
     */
    public static Map<String, List<Integer>> dictFaultName1Map = new HashMap<String, List<Integer>>();
    
    /**
     * code和对应的 故障名称2对应的通道号
     */
    public static Map<String, List<Integer>> dictFaultName2Map = new HashMap<String, List<Integer>>();
    
    public void init() {
        List<EquipmentFault> list = equipmentFaultDao.getAllTypes();
        // 清空缓存数据
        ErrorTypeCache.dictMap.clear();
        ErrorTypeCache.dictTypeMap.clear();
        ErrorTypeCache.dictJhTypeMap.clear();
        ErrorTypeCache.dictFaultName1Map.clear();
        ErrorTypeCache.dictFaultName2Map.clear();
        
        Map<String, String> dictMap = new HashMap<String, String>();
        Map<String, Integer> dictTypeMap = new HashMap<String, Integer>();
        Map<String, Integer> dictJhTypeMap = new HashMap<String, Integer>();
        Map<String, List<Integer>> dictFaultName1Map = new HashMap<String, List<Integer>>();
        Map<String, List<Integer>> dictFaultName2Map = new HashMap<String, List<Integer>>();
        for (EquipmentFault equipmentFault : list) {
            dictMap.put(equipmentFault.getCode()+equipmentFault.getEquipmentId(), equipmentFault.getName());
            dictTypeMap.put(equipmentFault.getCode()+equipmentFault.getEquipmentId(), equipmentFault.getType());
            dictJhTypeMap.put(equipmentFault.getCode()+equipmentFault.getEquipmentId(), equipmentFault.getJhType());
            
            //将需要显示 多名称中的 第一个名称的  通道号存起来
            if (1 == equipmentFault.getJhType()) {
            	List<Integer> dictFaultName1ChannelsList = new ArrayList<Integer>();
            	String[] faultName1Channels = equipmentFault.getActivationOne().split(",");
                for(int i=0;i<faultName1Channels.length;i++){
                	dictFaultName1ChannelsList.add(Integer.valueOf(faultName1Channels[i]));
                }
                dictFaultName1Map.put(equipmentFault.getCode()+equipmentFault.getEquipmentId(), dictFaultName1ChannelsList);
                
                
              //将需要显示 多名称中的 第一个名称的  通道号存起来
                List<Integer> dictFaultName2ChannelsList = new ArrayList<Integer>();
                String[] faultName2Channels = equipmentFault.getActivationTwo().split(",");
                for(int i=0;i<faultName2Channels.length;i++){
                	dictFaultName2ChannelsList.add(Integer.valueOf(faultName2Channels[i]));
                }
                dictFaultName2Map.put(equipmentFault.getCode()+equipmentFault.getEquipmentId(), dictFaultName2ChannelsList);
            }
            
            
        }
        ErrorTypeCache.dictMap = dictMap;
        ErrorTypeCache.dictTypeMap = dictTypeMap;
        ErrorTypeCache.dictJhTypeMap = dictJhTypeMap;
        ErrorTypeCache.dictFaultName1Map = dictFaultName1Map;
        ErrorTypeCache.dictFaultName2Map = dictFaultName2Map;
        System.out.println("字典数据大小： " + dictMap.size());
    }
    
    /**
     * 获取故障名称
     * @param code
     * @return
     */
    public static String getCacheDict(String code) {
        for (Map.Entry<String, String> entry : dictMap.entrySet()) {
            if (code.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return "未知故障";
    }
    
    /**
     * 获取故障类型
     * @param code
     * @return
     */
    public static Integer getCacheType(String code) {
        for (Map.Entry<String, Integer> entry : dictTypeMap.entrySet()) {
            if (code.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * 获取故障名称类型
     * @param code
     * @return
     */
    public static Integer getCacheJhType(String code) {
        for (Map.Entry<String, Integer> entry : dictJhTypeMap.entrySet()) {
            if (code.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return -1;
    }
    
    
    /**
     * 获取故障名称1的通道号
     * @param code
     * @return
     */
    public static List<Integer> getCacheFault1Channels(String code) {
        for (Map.Entry<String, List<Integer>> entry : dictFaultName1Map.entrySet()) {
            if (code.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
    
    /**
     * 获取故障名称2的通道号
     * @param code
     * @return
     */
    public static List<Integer> getCacheFault2Channels(String code) {
        for (Map.Entry<String, List<Integer>> entry : dictFaultName2Map.entrySet()) {
            if (code.equals(entry.getKey())) {
                return entry.getValue();
            }
        }
        return null;
    }
}
