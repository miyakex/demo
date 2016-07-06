package com.example.db.data;

import static com.example.db.interfaces.DBColumns.DBColType.INT;
import static com.example.db.interfaces.DBColumns.DBColType.STRING;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.db.interfaces.DBColumns;
import com.example.db.util.DaoHelper;
import com.example.domain.entity.Users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
public class UsersDaoImpl implements UsersDao {

	@RequiredArgsConstructor
	public enum UsersCols implements DBColumns<Users> {
		NAME(STRING, Users::getName, (d, o) -> d.setName((String)o)),
		AGE(INT, Users::getAge, (d, o) -> d.setAge((int)o)),
		;
		
		@Getter
		private final DBColType colType;
		@Getter
		private final ValueSupplier<Users> valueSupplier;
		@Getter
		private final ValueBinder<Users> valueBinder;
		
	}
	
	private DaoHelper<UsersCols, Users> daoHelper;
	
	private DaoHelper<UsersCols, Users> getHelper() {
		if (daoHelper == null) {
			daoHelper = new DaoHelper<>("users", UsersCols.class);
		}
		return daoHelper;
	}
	
	@Override
	public List<Users> selectAll() {
		return getHelper().selectAll(Users::new);
	}

	@Override
	public void insert(Users data) {
		getHelper().insert(data);
	}

}
