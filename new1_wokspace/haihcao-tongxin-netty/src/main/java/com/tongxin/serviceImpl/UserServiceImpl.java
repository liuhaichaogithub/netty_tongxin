package com.tongxin.serviceImpl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.tongxin.mapper.UsersMapper;
import com.tongxin.pojo.Users;
import com.tongxin.service.UserService;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UsersMapper userMapper;
	
	@Autowired
	private Sid sid;
	
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public boolean queryUsernameIsExist(String username) {
		Users user = new Users();
		user.setUsername(username);
		Users userResult = userMapper.selectOne(user);
		return null != userResult ? true : false ;
	}
	@Transactional(propagation = Propagation.SUPPORTS)
	@Override
	public Users queryUserForLogin(String userNmae, String password) {
		Example userExample = new Example(Users.class);
		Criteria criteria = userExample.createCriteria();
		criteria.andEqualTo("userNmae", userNmae);
		criteria.andEqualTo("password", password);
		Users userResult = userMapper.selectOneByExample(userExample);
		return userResult;
	}
	@Override
	public Users saveUser(Users user) {
		//二维码
		user.setQrcode("");
		user.setId(sid.nextShort());
		userMapper.insert(user);
		return user;
	}

}
