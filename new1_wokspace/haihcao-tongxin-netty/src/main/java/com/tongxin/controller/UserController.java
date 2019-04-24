package com.tongxin.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tongxin.pojo.Users;
import com.tongxin.pojo.vo.UsersVo;
import com.tongxin.service.UserService;
import com.tongxin.utils.JSONResult;
import com.tongxin.utils.MD5Utils;

@RestController
@RequestMapping("u")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/registOrLogin")
	public JSONResult registOrLogin(@RequestBody Users user) throws Exception {
		
		if(StringUtils.isBlank(user.getUsername())||StringUtils.isBlank(user.getPassword())) {
			return JSONResult.errorMsg("用户名或密码不能为空");
		}
		boolean queryUsernameIsExist = userService.queryUsernameIsExist(user.getUsername());
		Users userResult = null; 
		if(queryUsernameIsExist) {
			//登陆
			userResult = userService.queryUserForLogin(user.getUsername(), MD5Utils.getMD5Str(user.getPassword()));
			if(null == userResult) {
				return JSONResult.errorMsg("账户名或密码错误");
			}
			
		}else {
			//注册
			user.setNickname(user.getUsername());
			user.setFaceImage(null);
			user.setFaceImageBig(null);
			user.setPassword(MD5Utils.getMD5Str(user.getPassword()));
			userResult = userService.saveUser(user);
			
		}
		UsersVo userVo = new UsersVo();
		BeanUtils.copyProperties(userResult,userVo);
		return JSONResult.ok(userVo);
		
	}

}
