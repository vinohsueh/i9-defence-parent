package i9.defence.platform.api.controller;


import i9.defence.platform.model.Manager;
import i9.defence.platform.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * Created by 姜哲 on 2018/1/16--11:24
 * 经销商Controller
 */
@RestController
@RequestMapping("agency")
public class AgencyController {
    @Autowired
    private ManagerService managerService;

    /**
     * 分页查询经销商
     * @return
     */
    @RequestMapping("/pageAgency")
    public HashMap<String, Object> pageAgency() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        List<Manager> agencys = managerService.selectAllAgency();
        for(Manager manager:agencys){
            System.out.println("1--"+manager.getUsername());
            if(manager.getAgencyList()!=null){
                for(Manager manager1:manager.getAgencyList()){
                    System.out.println("2----"+manager1.getUsername());
                    if(manager1.getAgencyList()!=null){
                        for(Manager manager2:manager1.getAgencyList()){
                            System.out.println("3------"+manager2.getUsername());
                        }
                    }else {
                        System.out.println("无下属----3级");
                    }
                }
            }else {
                System.out.println("无下属----2级");
            }
        }
        result.put("data",agencys);
        return result;
    }

}
