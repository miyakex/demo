package com.example.db.data;

import static com.example.db.interfaces.DBColumns.DBColType.INT;
import static com.example.db.interfaces.DBColumns.DBColType.STRING;

import org.springframework.stereotype.Component;

import com.example.db.interfaces.DBColumns;
import com.example.db.util.DaoHelper;
import com.example.domain.entity.Person;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Component
public class PersonDaoImpl implements PersonDao {

	@RequiredArgsConstructor
	public enum PersonCols implements DBColumns<Person> {
		ID(STRING, Person::getId, (e, o) -> e.setId((String)o)),
		NAME(STRING, Person::getName, (e, o) -> e.setName((String)o)),
		AGE(INT, Person::getAge, (e, o) -> e.setAge((int)o)),
		;
		@Getter
		private final DBColType colType;
		@Getter
		private final ValueSupplier<Person> valueSupplier;
		@Getter
		private final ValueBinder<Person> valueBinder;
	}
	
	private DaoHelper<PersonCols, Person> daoHelper;
	
	DaoHelper<PersonCols, Person> getDaoHelper() {
		if (daoHelper == null) {
			daoHelper = new DaoHelper<>("PERSON", PersonCols.class);
		}
		return daoHelper;
	}
}
