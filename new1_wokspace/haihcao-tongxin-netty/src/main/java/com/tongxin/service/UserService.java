package com.tongxin.service;

import com.tongxin.pojo.Users;

public interface UserService {

	public boolean queryUsernameIsExist(String username);
	
	public Users queryUserForLogin(String userNmae,String password);
	
	public Users saveUser(Users user);
}
