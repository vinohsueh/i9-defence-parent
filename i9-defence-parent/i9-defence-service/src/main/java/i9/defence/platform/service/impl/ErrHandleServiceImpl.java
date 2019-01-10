package i9.defence.platform.service.impl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import i9.defence.platform.dao.EquipmentDao;
import i9.defence.platform.dao.ErrHandleDao;
import i9.defence.platform.dao.vo.ErrHandleSearchDto;
import i9.defence.platform.dao.vo.ErrHandleToExcel;
import i9.defence.platform.dao.vo.ErrHandleUnifiedDto;
import i9.defence.platform.dao.vo.HiddenDangerDto;
import i9.defence.platform.model.ErrHandle;
import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ErrHandleService;
import i9.defence.platform.service.ManagerService;
import i9.defence.platform.utils.BusinessException;
import i9.defence.platform.utils.Constants;
import i9.defence.platform.utils.ExcelBean;
import i9.defence.platform.utils.PageBounds;
import i9.defence.platform.utils.StringUtil;

@Service
@Transactional
public class ErrHandleServiceImpl implements ErrHandleService{

	@Autowired
	private ErrHandleDao errHandleDao;
	
	@Autowired
	private EquipmentDao equipmentDao;
	
	@Autowired
	private ManagerService managerService;
	
	@Override
	public void handlingErrors(ErrHandleUnifiedDto errHandleUnifiedDto) throws BusinessException {
		try {
			//当前登录人
			Manager manager = managerService .getLoginManager();
			//封装所有的历史记录
			List<ErrHandle> errHandles= new ArrayList<ErrHandle>();
			//封装所有的报警deviceId
			List<String> deviceIdsWarning=new ArrayList<String>(); 
			//封装所有的隐患deviceId
			List<String> deviceIdsHidden=new ArrayList<String>(); 
			//封装所有的离线deviceId
			List<Integer> EquipIdsOffLine=new ArrayList<Integer>(); 
			//根据ids获取到所有
			List<HiddenDangerDto> equipProblems = equipmentDao.selectHiddenDangerByIds(errHandleUnifiedDto.getEqIds());
			for(HiddenDangerDto hiddenDangerDto:equipProblems) {
				ErrHandle errHandle = new ErrHandle();
				errHandle.setHandleManagerId(manager.getId());
				errHandle.setHandleDate(new Date());
				errHandle.setHandleState(1);
				errHandle.setEqDeviceId(hiddenDangerDto.getDeviceId());
				errHandle.setEqAddRess(hiddenDangerDto.getEquipmentPosition());
				errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
				//离线
				if(0 ==hiddenDangerDto.getStatus()) {
					errHandle.setType(1); 
					EquipIdsOffLine.add(hiddenDangerDto.getId());
				}else{
					//报警
					if(1 ==hiddenDangerDto.getRemainAlert()) {
						errHandle.setType(2); 
						deviceIdsWarning.add(hiddenDangerDto.getDeviceId());
				    //隐患
					}else if(2 ==hiddenDangerDto.getRemainAlert()){
						errHandle.setType(3); 
						deviceIdsHidden.add(hiddenDangerDto.getDeviceId());
					}
				}
				errHandles.add(errHandle);
			}
			errHandleDao.addErrHandle(errHandles);
			if(0 !=EquipIdsOffLine.size()) {
				equipmentDao.updateEquipStatusByIds(EquipIdsOffLine);
			}
			if(0!= deviceIdsWarning.size()) {
				errHandleDao.updateHandleFault(deviceIdsWarning);
				equipmentDao.updateEquipRemainAlertByDeviceIds(deviceIdsWarning);
			}
			if(0!= deviceIdsHidden.size()) {
				errHandleDao.updateHandleHidden(deviceIdsHidden);
				equipmentDao.updateEquipRemainAlertByDeviceIds(deviceIdsHidden);
			}
			/*//设备
			Equipment equipment = equipmentDao.getEquipmentById(errHandleUnifiedDto.getEqId());
			//设备故障类型（1 故障）（2 报警）（3 隐患）
			Integer eqType = errHandleUnifiedDto.getEqType();
			if(eqType == 1) {
				errHandleDao.updateHandleFault(equipment.getDeviceId());
			}else if(eqType == 2) {
				errHandleDao.updateHandlePolice(equipment.getDeviceId());
			}else{
				errHandleDao.updateHandleHidden(equipment.getDeviceId());
			}
			ErrHandle errHandle = new ErrHandle();
			errHandle.setHandleManagerId(manager.getId());
			errHandle.setHandleDate(new Date());
			errHandle.setHandleState(1);
			errHandle.setEqDeviceId(equipment.getDeviceId());
			errHandle.setEqAddRess(equipment.getEquipmentPosition());
			errHandle.setType(eqType);
			errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
			errHandleDao.addErrHandle(errHandle);*/
		} catch (Exception e) {
			throw new BusinessException("批量处理设备错误失败", e.getMessage());
		}		
	}
	
	 @Override
	    public void handlingErrors2(ErrHandleUnifiedDto errHandleUnifiedDto) throws BusinessException {
	     try {
	            //当前登录人
	            Manager manager = managerService .getLoginManager();
	            //封装所有的历史记录
	            List<ErrHandle> errHandles= new ArrayList<ErrHandle>();
	            //封装所有的报警deviceId
	            List<String> deviceIdsWarning=new ArrayList<String>(); 
	            //封装所有的隐患deviceId
	            List<String> deviceIdsHidden=new ArrayList<String>(); 
	            //封装所有的离线deviceId
	            List<Integer> EquipIdsOffLine=new ArrayList<Integer>(); 
	            //根据ids获取到所有
	            List<HiddenDangerDto> equipProblems = equipmentDao.selectHiddenDangerByIds(errHandleUnifiedDto.getEqIds());
	            for(HiddenDangerDto hiddenDangerDto:equipProblems) {
	                ErrHandle errHandle = new ErrHandle();
	                errHandle.setHandleManagerId(manager.getId());
	                errHandle.setHandleDate(new Date());
	                errHandle.setHandleState(1);
	                errHandle.setEqDeviceId(hiddenDangerDto.getDeviceId());
	                errHandle.setEqAddRess(hiddenDangerDto.getEquipmentPosition());
	                errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
	                //离线
	                if(0 ==hiddenDangerDto.getStatus()) {
	                    errHandle.setType(1); 
	                    EquipIdsOffLine.add(hiddenDangerDto.getId());
	                }else{
	                    //报警
	                    if(1 ==hiddenDangerDto.getRemainAlert()) {
	                        errHandle.setType(2); 
	                        deviceIdsWarning.add(hiddenDangerDto.getDeviceId());
	                    //隐患
	                    }else if(2 ==hiddenDangerDto.getRemainAlert()){
	                        errHandle.setType(3); 
	                        deviceIdsHidden.add(hiddenDangerDto.getDeviceId());
	                    }
	                }
	                errHandles.add(errHandle);
	            }
	            errHandleDao.addErrHandle(errHandles);
	            if(0 !=EquipIdsOffLine.size()) {
	                equipmentDao.updateEquipStatusByIds(EquipIdsOffLine);
	            }
	            if(0!= deviceIdsWarning.size()) {
	                errHandleDao.updateHandleFault(deviceIdsWarning);
	                equipmentDao.updateEquipRemainAlertByDeviceIds2(deviceIdsWarning);
	            }
	            if(0!= deviceIdsHidden.size()) {
	                errHandleDao.updateHandleHidden(deviceIdsHidden);
	                equipmentDao.updateEquipRemainAlertByDeviceIds2(deviceIdsHidden);
	            }
	        } catch (Exception e) {
	            throw new BusinessException("批量处理设备错误失败", e.getMessage());
	        }
	    }

	@Override
	public void deleteErrHandle(List<Integer> ids) throws BusinessException {
		try {
			errHandleDao.deleteErrHandle(ids);
		} catch (Exception e) {
			throw new BusinessException("批量删除设备错误处理记录失败", e.getMessage());
		}
		
	}

	@Override
	public ErrHandle getErrHandleById(int id) throws BusinessException {
		try {
			return errHandleDao.getErrHandleById(id);
		} catch (Exception e) {
			throw new BusinessException("根据ID查询设备错误处理记录失败", e.getMessage());
		}
	}

	@Override
	public PageBounds<ErrHandle> selectByLimitPage(ErrHandleSearchDto errHandleSearchDto) throws BusinessException {
		try {
			//当前登录人
			Manager loginManager = managerService.getLoginManager();
			errHandleSearchDto.setManagerId(loginManager.getId());
			//如果为网站用户显示全部（type=0）
			if(Arrays.asList(Constants.S_NET_MANAGER).contains(loginManager.getType())) {
				return errHandleDao.selectByLimitPage(errHandleSearchDto);
			}
			//如果为经销商和管理员(type=1)
			else if (Arrays.asList(Constants.S_AGENCY_TYPE).contains(loginManager.getType())) {
				errHandleSearchDto.setManagerId(loginManager.getId());
				return errHandleDao.selectByLimitPage(errHandleSearchDto);
			}else if (Arrays.asList(Constants.S__Project_Type).contains(loginManager.getType())){
				//如果是项目管理员(type=2)
				errHandleSearchDto.setManagerId2(loginManager.getId());
				return errHandleDao.selectByLimitPage(errHandleSearchDto);
			}
		} catch (Exception e) {
			throw new BusinessException("分页查询设备错误处理记录失败", e.getMessage());
		}
		return null; 
	}

	@Override
	public void handleErrorDevice(List<String> list) throws BusinessException {
		try {
			//处理故障
			errHandleDao.updateBatchHandleFault(list);
			//处理报警
			errHandleDao.updateBatchHandlePolice(list);
			//处理隐患
			errHandleDao.updateBatchHandleHidden(list);
			//添加记录
			errHandleDao.addBatchHandle(list);
		} catch (Exception e) {
			throw new BusinessException("自动处理失败", e.getMessage());
		}
		
	}

	@Override
	public void errHandleEdit(ErrHandleUnifiedDto errHandleUnifiedDto) throws BusinessException {
		try {
				//当前登录人
				Manager manager = managerService.getLoginManager();
				//封装所有的历史记录
				List<ErrHandle> errHandles= new ArrayList<ErrHandle>();
				//封装所有的报警deviceId
				List<String> deviceIdsWarning=new ArrayList<String>(); 
				//封装所有的隐患deviceId
				List<String> deviceIdsHidden=new ArrayList<String>(); 
				//根据ids获取到所有
				List<HiddenDangerDto> equipProblems = equipmentDao.selectHiddenDangerByIds(errHandleUnifiedDto.getEqIds());
				for(HiddenDangerDto hiddenDangerDto:equipProblems) {
					ErrHandle errHandle = new ErrHandle();
					errHandle.setHandleManagerId(manager.getId());
					errHandle.setHandleDate(new Date());
					errHandle.setHandleState(1);
					errHandle.setEqDeviceId(hiddenDangerDto.getDeviceId());
					errHandle.setHandleCon(errHandleUnifiedDto.getHandleCon());
					if(1==hiddenDangerDto.getRemainAlert()) {
						errHandle.setType(2);
						deviceIdsWarning.add(hiddenDangerDto.getDeviceId());
					//隐患
					}else if(2==hiddenDangerDto.getRemainAlert()) {
						errHandle.setType(3);
						deviceIdsHidden.add(hiddenDangerDto.getDeviceId());
					}
					errHandles.add(errHandle);
				}
				errHandleDao.updateErrHandles(errHandles);
				if(0!= deviceIdsWarning.size()) {
					errHandleDao.updateHandleFault(deviceIdsWarning);
					equipmentDao.updateEquipRemainAlertByDeviceIds(deviceIdsWarning);
				}
				if(0!= deviceIdsHidden.size()) {
					errHandleDao.updateHandleHidden(deviceIdsHidden);
					equipmentDao.updateEquipRemainAlertByDeviceIds(deviceIdsHidden);
				}
		} catch (Exception e) {
			throw new BusinessException("处理失败", e.getMessage());
		}
		
	}

    @Override
    public XSSFWorkbook downLoadExportToExcel() throws BusinessException {
        try {
            SimpleDateFormat sdm = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<ErrHandleToExcel> list = errHandleDao.selectErrHandleToExcel();
            for(ErrHandleToExcel excel : list){
               String handleDateStr = sdm.format(excel.getHandleDate());
               excel.setHandleDateStr(handleDateStr);
            }
            List<ExcelBean> ems = new ArrayList<>();
            Map<Integer, List<ExcelBean>> map = new LinkedHashMap<>();
            ems.add(new ExcelBean("处理时间", "handleDateStr", 0));
            ems.add(new ExcelBean("状态", "typeStr", 0));
            ems.add(new ExcelBean("项目名称", "projectName", 0));
            ems.add(new ExcelBean("设备分类", "eqCategoryName", 0));
            ems.add(new ExcelBean("设备位置", "equipmentRemarks", 0));
            ems.add(new ExcelBean("处理内容", "handleStateStr",0));
            ems.add(new ExcelBean("处理人", "managerName",0));
            map.put(0, ems);
            List<HashMap<String, Object>> array = new ArrayList<HashMap<String, Object>>();
            for (ErrHandleToExcel dto : list) {
                //inquery.setMzId(inquery.getMZID());
                HashMap<String, Object> m = new HashMap<String, Object>();
                for (ExcelBean em : ems) {
                    PropertyDescriptor pd = new PropertyDescriptor(
                            em.getPropertyName(), ErrHandleToExcel.class);
                    Method getMethod = pd.getReadMethod();
                    Object value = getMethod.invoke(dto);
                    m.put(em.getPropertyName(), value);
                }
                array.add(m);
            }
            // 声明String数组，并初始化元素（表头名称
            //第一行表头字段，合并单元格时字段跨几列就将该字段重复几次
            String [] excelHeader0 = {"处理时间","状态",
                    "项目名称","设备分类", "设备位置","处理内容","处理人"};
            //  “0,2,0,0”  ===>  “起始行，截止行，起始列，截止列”
            String[] headnum0 = {"0,0,0,0","0,0,1,1","0,0,2,2","0,0,3,3","0,0,4,4","0,0,5,5","0,0,6,6"};
         // 声明一个工作簿
            XSSFWorkbook workbook = new XSSFWorkbook();
            // 生成一个表格
            XSSFSheet sheet = workbook.createSheet("历史记录表格");
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
//            row = sheet.createRow(1); 
//            for (int i = 0; i < excelHeader1.length; i++) {
 //
//                sheet.autoSizeColumn(i, true);// 自动调整宽度
//                XSSFCell cell = row.createCell(i);
//                cell.setCellValue(excelHeader1[i]);
//                cell.setCellStyle(fontStyle);
 //
//                if (i >= 1 && i <= 12) {
//                    for (int j = 0; j < excelHeader1.length; j++) {
//                        // 从第j+1列开始填充
//                        cell = row.createCell(j + 2);
//                        // 填充excelHeader1[j]第j个元素
//                        cell.setCellValue(excelHeader1[j]);
//                        cell.setCellStyle(fontStyle);
//                    }
//                }
//            }
          // 动态合并单元格
//          for (int i = 0; i < headnum1.length; i++) {
    //
//              sheet.autoSizeColumn(i, true);
//              String[] temp = headnum1[i].split(",");
//              Integer startrow = Integer.parseInt(temp[0]);
//              Integer overrow = Integer.parseInt(temp[1]);
//              Integer startcol = Integer.parseInt(temp[2]);
//              Integer overcol = Integer.parseInt(temp[3]);
//              sheet.addMergedRegion(new CellRangeAddress(startrow, overrow, startcol, overcol));
//          }
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
           }catch (Exception e) {
               throw new BusinessException("Excel导出失败",e.getMessage());
           }
    }
}
