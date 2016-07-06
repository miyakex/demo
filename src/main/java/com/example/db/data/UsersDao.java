package com.example.db.data;

import java.util.List;

import com.example.domain.entity.Users;

public interface UsersDao {

	List<Users> selectAll();
	
	void insert(Users data);
}
