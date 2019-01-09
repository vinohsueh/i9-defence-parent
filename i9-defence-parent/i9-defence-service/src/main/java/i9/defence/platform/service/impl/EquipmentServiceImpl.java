package i9.defence.platform.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.ApplyDao;
import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.ManagerDao;
import i9.defence.platform.dao.vo.DealStatusDto;
import i9.defence.platform.dao.vo.EqChannelDataDto;
import i9.defence.platform.dao.vo.EquipmentNewestDto;
import i9.defence.platform.dao.vo.EquipmentProjectDto;
import i9.defence.platform.dao.vo.EquipmentSearchDto;
import i9.defence.platform.dao.vo.HiddenDangerChannelDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.dao.vo.HiddenDangerSearchDto;
import i9.defence.platform.dao.vo.MonthData;
import i9.defence.platform.dao.vo.MonthDataDto;
import i9.defence.platform.dao.vo.TotalEquipmentDto;
import i9.defence.platform.model.Apply;
import i9.defence.platform.model.ChannelData;
import i9.defence.platform.model.Client;
import i9.defence.platform.model.Equipment;
import i9.defence.platform.model.Manager;
import i9.defence.platform.model.Passageway;
import i9.defence.platform.service.EquipmentService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.EncryptUtils;
import i9.defence.platform.utils.ExcelBean;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.StringUtil;
import i9.defence.platform.utils.TargetDataSource;
/**
 * 项目类别ServiceImpl
 * @author gbq
 * @create 2018年
 */
@Service
@Transactional
public class EquipmentServiceImpl implements EquipmentService {
	
	@Autowired
	private EquipmentDao equipmentDao;
	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private ManagerService managerService;
	@Autowired
	private ApplyDao applyDao; 
	@Resource
    private JdbcTemplate jdbcTemplate;
	@Override
	public PageBounds<Equipment> selectByLimitPage(EquipmentSearchDto equipmentSearchDto)
			throws BusinessException {
		try {
			//获取登录人
			Manager loginManager = managerService.getLoginManager();
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				return equipmentDao.selectByLimitPage(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize());
			}
			//如果为经销商和管理员
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				return equipmentDao.selectByLimitPage2(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize(),loginManager.getId());
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员
				return equipmentDao.selectByLimitPage3(equipmentSearchDto, equipmentSearchDto.getCurrentPage(), equipmentSearchDto.getPageSize(),loginManager.getId());
			}
		} catch (Exception e) {
			throw new BusinessException("查询设备失败",e.getMessage());
		}
		return null;
	}

	@Override
	public Equipment addEquipment(Equipment equipment) throws BusinessException {
		try {
			StringBuffer str = new StringBuffer();
			str.append(equipment.getSystemId()).append(EncryptUtils.bytesToHexString(EncryptUtils.intToBytes(equipment.getLoopl()))).append(equipment.getEquipmentPosition());
			equipment.setDeviceId(str.toString());
			//查询设备地址
			Equipment existEquipment=equipmentDao.findEquipmentDeviceId(equipment.getDeviceId());
			if(equipment.getId()!=null) {
				if(existEquipment != null && existEquipment.getId() - equipment.getId() !=0){
		            throw new BusinessException("设备已存在!");
				}
				equipmentDao.updateEquipment(equipment);
			}else {
				if(existEquipment != null){
					throw new BusinessException("设备已存在!");
				}
				equipment.setEquipmentDate(new Date());
				equipmentDao.addEquipment(equipment);
			}
			return equipment;
		}catch (BusinessException e) {
			throw new BusinessException(e.getErrorMessage());
		}catch (Exception e) {
			throw new BusinessException("添加设备失败",e.getMessage());
		}
	}
	
	@TargetDataSource("xfjcxt")
	@Override
	public void addEquipmentToOldPlat(final Equipment equipment) throws BusinessException {
		String querySql = "select num from device_list where device_code = ?";
		final List<Integer> nums = jdbcTemplate.queryForList(querySql, Integer.class,equipment.getId());
		if (nums.size()>0){
			String sql = "update device_list set device_type =?,device_code=?,device_name=?,device_org=?,device_address=?,longitude=?,latitude=?,remark=?,link_man=?,phone=? where num = ?";  
	        jdbcTemplate.update(sql,new PreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,equipment.getEquipmentSystemtype().getSystemType());
					ps.setInt(2,equipment.getId());
				    ps.setString(3,equipment.getEquipmentCategory().getEqCategoryName());
				    ps.setString(4, equipment.getProject().getProjectName());
				    ps.setString(5, equipment.getProject().getProjectAddressStr());
				    ps.setString(6, equipment.getProject().getProjectLongitude().toString());
				    ps.setString(7, equipment.getProject().getProjectLatitude().toString());
				    ps.setString(8, equipment.getEquipmentRemarks());
				    List<Client> list = equipment.getProject().getClientList();
				    ps.setString(9, null);
				    ps.setString(10, null);
				    if (list.size()>0){
				    	ps.setString(9, list.get(0).getName());
					    ps.setString(10, list.get(0).getPhone());
				    }
				    ps.setInt(11, nums.get(0));
				    
				}
	        	
	        });  
		}else{
			String sql = "insert into device_list(device_type,device_code,device_name,device_org,device_address,longitude,latitude,remark,link_man,phone) values(?,?,?,?,?,?,?,?,?,?)";  
	        jdbcTemplate.update(sql,new PreparedStatementSetter(){
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1,equipment.getEquipmentSystemtype().getSystemType());
					ps.setInt(2,equipment.getId());
				    ps.setString(3,equipment.getEquipmentCategory().getEqCategoryName());
				    ps.setString(4, equipment.getProject().getProjectName());
				    ps.setString(5, equipment.getProject().getProjectAddressStr());
				    ps.setString(6, equipment.getProject().getProjectLongitude().toString());
				    ps.setString(7, equipment.getProject().getProjectLatitude().toString());
				    ps.setString(8, equipment.getEquipmentRemarks());
				    List<Client> list = equipment.getProject().getClientList();
				    ps.setString(9, null);
				    ps.setString(10, null);
				    if (list.size()>0){
				    	ps.setString(9, list.get(0).getName());
					    ps.setString(10, list.get(0).getPhone());
				    }
				    
				}
	        	
	        });  
		}
	}
	
	@Override
	public void updateEquipment(Equipment equipment) throws BusinessException {
		try {
			equipmentDao.updateEquipment(equipment);
		} catch (Exception e) {
			throw new BusinessException("更新设备失败",e.getMessage());
		}
	}

	@Override
	public Equipment getEquipmentById(int id) throws BusinessException {
		try {
			return equipmentDao.getEquipmentById(id);
		} catch (Exception e) {
			throw new BusinessException("查询设备失败",e.getMessage());
		}
	}

	@Override
	public String  applyDelEquipment(List<Integer> ids) throws BusinessException {
		try { 
			//1.获得当前登录用户 
			Manager manager = managerService.getLoginManager();
			//1.1接受前台所要删除的设备List<Integer> ids
			List<Equipment> listEquipment = equipmentDao.getEquipmentByIds(ids);
			//1.2储存更新后的设备
			ArrayList<Equipment> listNewEquip = new ArrayList<>(); 
			//1.3创建一个List包装所有apply申请
			List<Apply> listApply = new ArrayList<>();
			//1.4查询此经销商是否有父级
			Integer parentById = managerDao.selectParentById(manager.getId()); 
			//1.5判断是否被申请过
			if(!Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
				//查询此人提交的删除项目申请在数据库中是否存在
				Integer number = applyDao.selectEquipmentCount(ids);
				if (number > 0) {
					//有人提交 过了
					throw new BusinessException("您选中的数据已经被申请过了");
				}else {
					//2. 判断当前用户的身份（type：0 网站用户；type：1 经销商；type：2.项目管理员）
					if(manager!=null) {
						//2.2如果为经销商
						  if(Arrays.asList(Constants.S_AGENCY_TYPE).contains(manager.getType())) {
							//2.2.1若为顶级经销商，处理人为null，交给管理员处理。
							if(null==parentById){
								for(Equipment equipment:listEquipment){
									Apply apply = new Apply();
									apply.setType(1);
									apply.setState(0);
									apply.setApplyId(manager.getId()); 
									apply.setApplyDate(new Date());
									apply.setConductorId(null);
									apply.setEquipmentId(equipment.getId());
									equipment.setDelStatus(0);
									listApply.add(apply);
									listNewEquip.add(equipment);
								}
								//3.插入到apply表中。
								applyDao.insertEquipmentApplys(listApply); 	
								equipmentDao.updateEquipments(listNewEquip);
								return "您的申请已提交，请等待您的上级处理";
								//2.2.2此经销商有父级的话交给上级经销商处理;
							}else {
								for(Equipment equipment:listEquipment){
									Apply apply = new Apply();
									apply.setType(1);
									apply.setState(0);
									apply.setApplyId(manager.getId()); 
									apply.setApplyDate(new Date());
									apply.setConductorId(parentById);
									apply.setEquipmentId(equipment.getId());
									equipment.setDelStatus(0);
									listNewEquip.add(equipment);
									listApply.add(apply);
								} 
								//3.插入到apply表中。
								applyDao.insertEquipmentApplys(listApply); 	
								equipmentDao.updateEquipments(listNewEquip);
								return "您的申请已提交，请等待您的上级处理";
							}
						}
						//2.3如果项目管理员,交给上级经销商处理。
						else if(Arrays.asList(Constants.S__Project_Type).contains(manager.getType())) {
							//2.3.1添加到apply记录。
							for(Equipment equipment:listEquipment){
								Apply apply = new Apply();
								apply.setType(1);
								apply.setState(0);
								apply.setApplyId(manager.getId()); 
								apply.setApplyDate(new Date());
								apply.setConductorId(equipment.getProject().getDistributorId());
								apply.setEquipmentId(equipment.getId());
								equipment.setDelStatus(0);
								listNewEquip.add(equipment);
								listApply.add(apply);
							}
							//3.插入到apply表中。
							applyDao.insertEquipmentApplys(listApply); 	
							equipmentDao.updateEquipments(listNewEquip);
							return "您的申请已提交，请等待您的上级处理";
						}
					}
				}
			}	
			//2.1 如果为网站用户，直接删除。
			else if(Arrays.asList(Constants.S_NET_MANAGER).contains(manager.getType())) {
				//真删
				this.deleteEquipment(ids);
				return "成功"; 
				//假删
				/*for(Equipment equipment:listEquipment){
			Apply apply = new Apply();
			apply.setType(1);
			apply.setState(0);
			apply.setApplyId(manager.getId()); 
			apply.setApplyDate(new Date());
			apply.setConductorId(null);
			apply.setEquipmentId(equipment.getId());
			listApply.add(apply);
		}*/
			}
		}
		catch (BusinessException e) {
				throw new BusinessException(e.getErrorMessage());
		} catch (Exception e) {
			   throw new BusinessException("申请删除设备类别失败",e.getMessage());
		}
		return null; 
	}

	@Override
	public void deleteEquipment(List<Integer> ids) throws BusinessException {
		try {
			  equipmentDao.deleteEquipment(ids);  
		} catch (Exception e) {
			 throw new BusinessException("删除设备类别失败",e.getMessage());
		}
	}

	@Override
	public List<Passageway> selectPassagewayByEid(String systemId) throws BusinessException {
		try {
			return equipmentDao.selectPassagewayByEid(systemId);
		} catch (Exception e) {
			throw new BusinessException("根据设备id查询通道失败",e.getMessage());
		}
	}

	@Override
	public void insertPassageWay(Passageway passageway) throws BusinessException {
		try {
			equipmentDao.insertPassageWay(passageway);
		} catch (Exception e) { 
				throw new BusinessException("新增通道失败",e.getMessage());
		}
		
	}

	@Override
	public PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage(HiddenDangerSearchDto hiddenDangerSearchDto)
			throws BusinessException {
		try {
			//获取登录人
			Manager loginManager = managerService.getLoginManager();
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				return equipmentDao.selectHiddenDangerByLimitPage(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
			}
			//如果为经销商和管理员
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				hiddenDangerSearchDto.setDistributorId(loginManager.getId());
				return equipmentDao.selectHiddenDangerByLimitPage(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员
				hiddenDangerSearchDto.setPrijrctManagerId(loginManager.getId());
				return equipmentDao.selectHiddenDangerByLimitPage(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
			}
		} catch (Exception e) {
			throw new BusinessException("分页报警隐患查询失败",e.getMessage());
		}
		return null;
	}
	
	@Override
	public PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage2(HiddenDangerSearchDto hiddenDangerSearchDto)
			throws BusinessException {
		try {
			//获取登录人
			Manager loginManager = managerService.getLoginManager();
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				return equipmentDao.selectHiddenDangerByLimitPage2(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
			}
			//如果为经销商和管理员
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				hiddenDangerSearchDto.setDistributorId(loginManager.getId());
				return equipmentDao.selectHiddenDangerByLimitPage2(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员
				hiddenDangerSearchDto.setPrijrctManagerId(loginManager.getId());
				return equipmentDao.selectHiddenDangerByLimitPage2(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
			}
		} catch (Exception e) {
			throw new BusinessException("分页报警隐患查询失败",e.getMessage());
		}
		return null;
	}
	
	@Override
    public PageBounds<HiddenDangerDto> selectHiddenDangerByLimitPage3(HiddenDangerSearchDto hiddenDangerSearchDto)
            throws BusinessException {
        try {
            //获取登录人
            Manager loginManager = managerService.getLoginManager();
            //如果为网站用户显示全部（type=0）
            if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
                return equipmentDao.selectHiddenDangerByLimitPage3(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
            }
            //如果为经销商和管理员
            else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
                hiddenDangerSearchDto.setDistributorId(loginManager.getId());
                return equipmentDao.selectHiddenDangerByLimitPage3(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
            }else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
                //如果是项目管理员
                hiddenDangerSearchDto.setPrijrctManagerId(loginManager.getId());
                return equipmentDao.selectHiddenDangerByLimitPage3(hiddenDangerSearchDto, hiddenDangerSearchDto.getCurrentPage(), hiddenDangerSearchDto.getPageSize());
            }
        } catch (Exception e) {
            throw new BusinessException("分页报警隐患查询失败",e.getMessage());
        }
        return null;
    }

	@Override
	public List<HiddenDangerChannelDto> selectHiddenDangerChannelDtoBySid(String deviceId,int count) throws BusinessException {
		try {
			return equipmentDao.selectHiddenDangerChannelDtoBySid(deviceId,count);
		} catch (Exception e) {
			throw new BusinessException("根据设备编号查询报警隐患失败",e.getMessage());
		}
	}

	/*@Override
	public List<HiddenDangerDto> getAllHiddenDanger(HiddenDangerSearchDto hiddenDangerSearchDto) throws BusinessException {
		
		try {
			//获取登录人
			Manager loginManager = managerService.getLoginManager();
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				List<HiddenDangerDto> list = equipmentDao.getAllHiddenDanger(hiddenDangerSearchDto);
				return list;
			}
			//如果为经销商和管理员
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				hiddenDangerSearchDto.setDistributorId(loginManager.getId());
				List<HiddenDangerDto> list = equipmentDao.getAllHiddenDanger(hiddenDangerSearchDto);
				return list;
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员
				hiddenDangerSearchDto.setPrijrctManagerId(loginManager.getId());
				List<HiddenDangerDto> list = equipmentDao.getAllHiddenDanger(hiddenDangerSearchDto);
				return list;
			}
		}catch (Exception e) {
			throw new BusinessException("查询全部失败",e.getMessage());
		}
		return null;
	}*/

	@Override
	public void updateDealStatus(List<DealStatusDto> list,Integer managerId,Date nowDate) throws BusinessException {
		try {
			equipmentDao.updateDealStatus(list,managerId,nowDate);
		} catch (Exception e) {
			throw new BusinessException("修改报警隐患失败",e.getMessage());
		}
	}

	@Override
	public List<HiddenDangerChannelDto> selectDangerChannelDtoBySid(String deviceId,int count) throws BusinessException {
		try {
			return equipmentDao.selectDangerChannelDtoBySid(deviceId,count);
		} catch (Exception e) {
			throw new BusinessException("根据设备编号查询报警隐患失败",e.getMessage());
		}
	}

	@Override
	public PageBounds<Equipment> selectErrorEquipment(EquipmentSearchDto equipmentSearchDto) throws BusinessException {
		try {
			return equipmentDao.selectErrorEquipment(equipmentSearchDto);
		} catch (Exception e) {
			throw new BusinessException("分页查询故障设备失败",e.getMessage());
		}
	}

	@Override
	public List<ChannelData> selectErrorRecord(EquipmentSearchDto equipmentSearchDto) throws BusinessException {
		try {
			return equipmentDao.selectErrorRecord(equipmentSearchDto);
		} catch (Exception e) {
			throw new BusinessException("查询故障记录失败",e.getMessage());
		}
	}

	@Override
	public Equipment getEquipmentByIdentifier(String deviceId) throws BusinessException {
		try {
			return equipmentDao.getEquipmentByIdentifier(deviceId);
		} catch (Exception e) {
			throw new BusinessException("查询设备失败",e.getMessage());
		}
	}

	@Override
	public HiddenDangerDto selectHiddenDangerDtoByDeviceId(String deviceId) throws BusinessException {
		try {
			return equipmentDao.selectHiddenDangerDtoByDeviceId(deviceId);
		} catch (Exception e) {
			throw new BusinessException("查询隐患数量失败",e.getMessage());
		}
	}

	@Override
	public List<MonthData> selectMonthWarningData(MonthDataDto monthDataDto) throws BusinessException {
		try {
			return equipmentDao.selectMonthWarningData(monthDataDto);
		} catch (Exception e) {
			throw new BusinessException("查询隐患数量失败",e.getMessage());
		}
	}

	@Override
	public List<MonthData> selectHiddenMonthData(MonthDataDto monthDataDto) throws BusinessException {
		try {
			return equipmentDao.selectHiddenMonthData(monthDataDto);
		} catch (Exception e) {
			throw new BusinessException("查询隐患数量失败",e.getMessage());
		}
	}

	@Override
	public List<HiddenDangerDto> selectAllHiddenDangerEdit(HiddenDangerSearchDto hiddenDangerSearchDto)
			throws BusinessException {
		try {
			Manager loginManager = managerService.getLoginManager();
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				List<HiddenDangerDto> list = equipmentDao.selectAllHiddenDangerEdit(hiddenDangerSearchDto);
				return list;
			}
			//如果为经销商和管理员
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				hiddenDangerSearchDto.setDistributorId(loginManager.getId());
				List<HiddenDangerDto> list = equipmentDao.selectAllHiddenDangerEdit(hiddenDangerSearchDto);
				return list;
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员
				hiddenDangerSearchDto.setPrijrctManagerId(loginManager.getId());
				List<HiddenDangerDto> list = equipmentDao.selectAllHiddenDangerEdit(hiddenDangerSearchDto);
				return list;
			}
//			return equipmentDao.selectAllHiddenDangerEdit(hiddenDangerSearchDto);
		} catch (Exception e) {
			throw new BusinessException("查询失败",e.getMessage());
		}
		return null;
	}

	@Override
	public EquipmentProjectDto selectDataAndManager(int id) throws BusinessException {
		try {
			return equipmentDao.selectDataAndManager(id);
		} catch (Exception e) {
			throw new BusinessException("查询失败",e.getMessage());
		}
	}
	public TotalEquipmentDto selectTotalEquipmentDto(MonthDataDto monthDataDto) throws BusinessException {
		try {
			return equipmentDao.selectTotalEquipmentDto(monthDataDto);
		} catch (Exception e) {
			throw new BusinessException("查询失败",e.getMessage());
		}	
	}

	@Override
	public List<HiddenDangerDto> selectHiddenDangerByIds(List<Integer> ids)throws BusinessException {
		try {
			return equipmentDao.selectHiddenDangerByIds(ids);
		} catch (Exception e) {
			throw new BusinessException("查询失败",e.getMessage());
		}
	}

    @Override
    public void updateAllEquipmentStatus() throws BusinessException {
        try {
            equipmentDao.updateAllEquipmentStatus();
        } catch (Exception e) {
            throw new BusinessException("更新所有设备状态失败",e.getMessage());
        }
    }

    @Override
    public void updateEquipmentNewestTime(String eqDeviceId, String dateTime) {
        try {
            equipmentDao.updateEquipmentNewestTime(eqDeviceId,dateTime);
        } catch (Exception e) {
            throw new BusinessException("更新设备最新事件事件失败",e.getMessage());
        }
    }

    @Override 
    public List<EquipmentNewestDto> selectAllEquipmentNewest() {
        try {
            return equipmentDao.selectAllEquipmentNewest();
        } catch (Exception e) {
            throw new BusinessException("更新设备最新事件事件失败",e.getMessage());
        }
       
    }

    @Override
    public void updateSomeStatusByDevicedIds(ArrayList<String> eqDeviceIdList) throws BusinessException {
        try {
            equipmentDao.updateSomeStatusByDevicedIds(eqDeviceIdList);
        } catch (Exception e) {
            throw new BusinessException("更新设备状态失败",e.getMessage());
        }
    }

    @Override
    public XSSFWorkbook downLoadExportToExcel(HiddenDangerSearchDto hiddenDangerSearchDto) {
        try {
           List<HiddenDangerDto> list = equipmentDao.selectAllHiddenDangerEdit2(hiddenDangerSearchDto);
           for(HiddenDangerDto hiddenDangerDto : list){
               List<EqChannelDataDto> eqChannelDataList1 = hiddenDangerDto.getEqChannelDataList();
               String s = "";
               for(int i =0 ; i < eqChannelDataList1.size(); i++){
                   String  str1 = eqChannelDataList1.get(i).getChannelName()+":"+eqChannelDataList1.get(i).getChannelValue();
                   s = s + str1;
                  /* String  str2 = eqChannelDataList1.get(1).getChannelName()+":"+eqChannelDataList1.get(i).getChannelValue();
                   hiddenDangerDto.setChannelValue(str2);*/
               }
               hiddenDangerDto.setChannelName(s);
           }
           List<ExcelBean> ems = new ArrayList<>();
           Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
           ems.add(new ExcelBean("设备名称", "eqCategoryName", 0));
           ems.add(new ExcelBean("设备地址", "equipmentPosition", 0));
           ems.add(new ExcelBean("设备位置", "equipmentRemarks", 0));
           ems.add(new ExcelBean("项目名称", "projectName", 0));
           ems.add(new ExcelBean("状态", "datastatustr", 0));
           ems.add(new ExcelBean("最新数据", "channelName",0));
           map.put(0, ems);
           List<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();
           for (HiddenDangerDto dto : list) {
               //inquery.setMzId(inquery.getMZID());
               HashMap<String, Object> m = new HashMap<String, Object>();
               for (ExcelBean em : ems) {
                   PropertyDescriptor pd = new PropertyDescriptor(
                           em.getPropertyName(), HiddenDangerDto.class);
                   Method getMethod = pd.getReadMethod();
                   Object value = getMethod.invoke(dto);
                   m.put(em.getPropertyName(), value);
               }
               array.add(m);
           }
           // 声明String数组，并初始化元素（表头名称
           //第一行表头字段，合并单元格时字段跨几列就将该字段重复几次
           String [] excelHeader0 = {"设备名称","设备地址",
                   "设备位置","项目名称",
                   "状态","最新数据"};
           //  “0,2,0,0”  ===>  “起始行，截止行，起始列，截止列”
           String[] headnum0 = {"0,0,0,0","0,0,1,1","0,0,2,2","0,0,3,3","0,0,4,4"};
        // 声明一个工作簿
           XSSFWorkbook workbook = new XSSFWorkbook();
           // 生成一个表格
           XSSFSheet sheet = workbook.createSheet("实时监控表格");
           // 表头
           XSSFCellStyle fontStyle = workbook.createCellStyle(); 
           XSSFFont font1 = workbook.createFont();
           font1.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
           font1.setFontName("黑体");
           font1.setFontHeightInPoints((short) 14);// 设置字体大小
           fontStyle.setFont(font1);
           fontStyle.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
           fontStyle.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
           fontStyle.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
           fontStyle.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
           fontStyle.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
           // 内容
           XSSFCellStyle fontStyle2 = workbook.createCellStyle();
           XSSFFont font2 = workbook.createFont();
           font2.setFontName("宋体");
           font2.setFontHeightInPoints((short) 10);// 设置字体大小
           fontStyle2.setFont(font2);
           fontStyle2.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
           fontStyle2.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
           fontStyle2.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
           fontStyle2.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
           fontStyle2.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
           // 生成表格的第一行
           // 第一行表头
           XSSFRow row = sheet.createRow(0);
           for(int i=0;i<excelHeader0.length;i++) {
               sheet.autoSizeColumn(i,true);// 根据字段长度自动调整列的宽度
               XSSFCell cell = row.createCell(i);
               cell.setCellValue(excelHeader0[i]);
               cell.setCellStyle(fontStyle);
           }
           // 动态合并单元格
           for(int i=0;i<headnum0.length;i++) {
               sheet.autoSizeColumn(i, true);
               String[] temp = headnum0[i].split(",");
               Integer startrow = Integer.parseInt(temp[0]);
               Integer overrow = Integer.parseInt(temp[1]);
               Integer startcol = Integer.parseInt(temp[2]);
               Integer overcol = Integer.parseInt(temp[3]);
               sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
          }
           // 第二行表头
//           row = sheet.createRow(1); 
//           for (int i = 0; i < excelHeader1.length; i++) {
//
//               sheet.autoSizeColumn(i, true);// 自动调整宽度
//               XSSFCell cell = row.createCell(i);
//               cell.setCellValue(excelHeader1[i]);
//               cell.setCellStyle(fontStyle);
//
//               if (i >= 1 && i <= 12) {
//                   for (int j = 0; j < excelHeader1.length; j++) {
//                       // 从第j+1列开始填充
//                       cell = row.createCell(j + 2);
//                       // 填充excelHeader1[j]第j个元素
//                       cell.setCellValue(excelHeader1[j]);
//                       cell.setCellStyle(fontStyle);
//                   }
//               }
//           }
         // 动态合并单元格
//         for (int i = 0; i < headnum1.length; i++) {
   //
//             sheet.autoSizeColumn(i, true);
//             String[] temp = headnum1[i].split(",");
//             Integer startrow = Integer.parseInt(temp[0]);
//             Integer overrow = Integer.parseInt(temp[1]);
//             Integer startcol = Integer.parseInt(temp[2]);
//             Integer overcol = Integer.parseInt(temp[3]);
//             sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
//         }
           // 第三行数据
           int rowindex = map.size();
           int maxKey = 0;
           for (Map.Entry<Integer, List<ExcelBean>> entry : map.entrySet()) {
               if (entry.getKey() > maxKey) {
                   maxKey = entry.getKey();
               }
           }
           List<ExcelBean> ems1 = map.get(maxKey);
           List<Integer> widths = new ArrayList<Integer>(ems1.size());
           for (HashMap<String, Object> obj : array) {
               XSSFRow row2 = sheet.createRow(rowindex);
               for (int i = 0; i < ems1.size(); i++) {
                   ExcelBean em = (ExcelBean) ems1.get(i);
                   Object rtn = obj.get(em.getPropertyName());
                   String value = "";
                   // 如果是日期类型 进行 转换
                   if (rtn != null) {
                       if (rtn instanceof Date) {
                           value = StringUtil.dateToStringWithoutTime((Date) rtn);
                       } else if (rtn instanceof BigDecimal) {
                           NumberFormat nf = new DecimalFormat("#,##0.00");
                           value = nf.format((BigDecimal) rtn).toString();
                       } else if ((rtn instanceof Integer) && (Integer.valueOf(rtn.toString()) < 0)) {
                           value = "--";
                       } else {
                           value = rtn.toString();
                       }
                   }
                   XSSFCell cell = row2.createCell(i);
                   cell.setCellValue(value);
                   cell.setCellType(XSSFCell.CELL_TYPE_STRING);
                   cell.setCellStyle(fontStyle2);
                   // 获得最大列宽
                   int width = value.getBytes().length * 300;
                   // 还未设置，设置当前
                   if (widths.size() <= i) {
                       widths.add(width);
                       continue;
                   }
                   // 比原来大，更新数据
                   if (width > widths.get(i)) {
                       widths.set(i, width);
                   }
               }
               rowindex++;
           }
           // 转换
           return workbook;
        } catch (Exception e) {
            throw new BusinessException("Excel导出失败",e.getMessage());
        }
    }

    @Override
    public List<MonthData> selectCodeMonthData(MonthDataDto monthDataDto) {
        try {
            return equipmentDao.selectCodeMonthData(monthDataDto);
        } catch (Exception e) {
            throw new BusinessException("查询故障数量失败",e.getMessage());
        }
    }

    @Override
    public List<String> selectCodename() {
        try {
            return equipmentDao.selectCodename();
        } catch (Exception e) {
            throw new BusinessException("查询故障名称失败",e.getMessage());
        }
    }

    @Override
    public List<MonthData> selectConnectLogMonthData(MonthDataDto monthDataDto) throws BusinessException {
        try {
            return equipmentDao.selectConnectLogMonthData(monthDataDto);
        } catch (Exception e) {
            throw new BusinessException("查询故障名称失败",e.getMessage());
        }
    }

}

