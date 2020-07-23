package com.app.database;

import java.util.ArrayList;
import java.util.List;

import com.app.entity.ISBN;
import com.app.entity.LoginUser;
import com.app.model.User;

public class MathDo {
	
	public LoginUser getUserInfo(String userName, List<LoginUser> userList) {
		LoginUser user = null;
		for(LoginUser getUser: userList) {
			if(getUser.getUserName().equals(userName)) {
				user = getUser;
				break;
			}
		}
		return user;
	}
}
