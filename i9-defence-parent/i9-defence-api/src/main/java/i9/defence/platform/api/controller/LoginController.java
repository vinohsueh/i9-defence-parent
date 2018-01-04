package i9.defence.platform.api.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vino.tiku.dto.AlertNum;
import com.vino.tiku.dto.UserDto;
import com.vino.tiku.dto.UserInfoDto;
import com.vino.tiku.dto.UserSign;
import com.vino.tiku.entity.Permission;
import com.vino.tiku.entity.Role;
import com.vino.tiku.entity.User;
import com.vino.tiku.service.PermissionService;
import com.vino.tiku.service.RoleService;
import com.vino.tiku.service.UserService;
import com.vino.tiku.utils.BusinessException;

@Controller
@RequestMapping("")
public class LoginController {
	
	@RequestMapping("/index.html")
	public String index() {
		return "index";
	}
	
	
	@RequestMapping("/")
    public String defaultPage() {
        return "redirect:/index.html";
    }
	
	@RequestMapping("/login.html")
	public String toLogin(@ModelAttribute User user) {
		return "login";
	}
	
	
	/*@RequestMapping(value="/login",method = RequestMethod.POST)
    public ModelAndView login(@ModelAttribute User user){
        try {
            User currentUser = userService.login(user);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date now=new Date();
            String date = sdf.format(now);
            UserSign userSign = new UserSign();
            userSign.setUser_id(currentUser.getId());
            userSign.setDate_day(date);
            userService.addUserSign(userSign);
            return new ModelAndView("redirect:index.html");
        } catch (BusinessException e) {
            return new ModelAndView("login").addObject("exception", e);
        }
        
    }*/
	

	@RequestMapping("/regist.html")
	public String toRegist() {
		return "regist";
	}

	/*@RequestMapping(value = "/regist.zhtml", method = RequestMethod.POST)
	public ModelAndView regist(@Valid UserDto userDto, Errors errors,HttpSession session) {
		if (errors.hasErrors()) {
			List<ObjectError> list = errors.getAllErrors();
			String errorString = list.get(list.size()-1).getDefaultMessage();
			return new ModelAndView("regist").addObject("exception",new BusinessException(errorString));
		}
		try {
			userService.regist(userDto,session);
		} catch (BusinessException e) {
			return new ModelAndView("regist").addObject("exception",e);
		}
		
		return new ModelAndView("regist").addObject("exception",new BusinessException("注册成功"));
	}*/

}
